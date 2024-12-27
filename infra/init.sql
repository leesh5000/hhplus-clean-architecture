-- 새로운 어드민 계정 생성
GRANT ALL PRIVILEGES ON *.* TO 'hhplus'@'%' WITH GRANT OPTION;

-- 새로운 데이터베이스 생성
DROP DATABASE IF EXISTS `hhplus`;
CREATE DATABASE `hhplus`;
USE `hhplus`;

-- 유저 테이블 생성
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`      BIGINT          NOT NULL AUTO_INCREMENT,
  `name`    VARCHAR(255)    NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 기본 유저 생성
INSERT INTO `user` (`name`) VALUES ('admin');

-- 특강 테이블 생성
DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
    `id`      BIGINT          NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(255)    NOT NULL,
    `speaker` VARCHAR(255)    NOT NULL,
    `capacity` INT            NOT NULL DEFAULT 30,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 특강 데이터 생성
INSERT INTO `course` (`name`, `speaker`, `capacity`) VALUES ('Spring Boot', '홍길동', 30);
INSERT INTO `course` (`name`, `speaker`, `capacity`) VALUES ('React', '김철수', 30);
INSERT INTO `course` (`name`, `speaker`, `capacity`) VALUES ('Vue', '이영희', 30);

-- 특강 스케쥴 테이블 생성
DROP TABLE IF EXISTS `course_schedule`;
CREATE TABLE IF NOT EXISTS `course_schedule` (
    `id`                BIGINT          NOT NULL AUTO_INCREMENT,
    `status`            ENUM('OPEN', 'CLOSE', 'FULL') NOT NULL DEFAULT 'OPEN',
    `current_applicants` INT    NOT NULL DEFAULT 0,
    `opening_date`      DATE    NOT NULL DEFAULT CURRENT_DATE,
    `course_id` BIGINT          NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 특강 스케쥴 데이터 생성
INSERT INTO `course_schedule` (`status`, `current_applicants`, `opening_date`, `course_id`) VALUES ('OPEN', 0, '2021-08-01', 1);
INSERT INTO `course_schedule` (`status`, `current_applicants`, `opening_date`, `course_id`) VALUES ('OPEN', 0, '2021-08-01', 2);
INSERT INTO `course_schedule` (`status`, `current_applicants`, `opening_date`, `course_id`) VALUES ('OPEN', 0, '2021-08-01', 3);

-- 특강 신청 테이블 생성
DROP TABLE IF EXISTS `registration`;
CREATE TABLE IF NOT EXISTS `registration` (
    `id`            BIGINT          NOT NULL AUTO_INCREMENT,
    `user_id`       BIGINT          NOT NULL,
    `course_schedule_id` BIGINT    NOT NULL,
    `registration_date` DATE        NOT NULL DEFAULT CURRENT_DATE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

FLUSH PRIVILEGES;