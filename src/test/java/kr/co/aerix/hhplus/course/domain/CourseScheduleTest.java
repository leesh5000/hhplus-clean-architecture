package kr.co.aerix.hhplus.course.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CourseScheduleTest {

    @DisplayName("강좌 스케쥴 상태가 `OPEN`이고 신청자가 30명 미만일 때, 신청 가능해야 한다.")
    @Test
    void canRegister() {
        // given
        LocalDate openingDate = LocalDate.now();
        CourseSchedule courseSchedule = new CourseSchedule(1L, openingDate);

        // when
        boolean result = courseSchedule.canRegister();

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("신청자가 30명이면, 강좌 스케쥴 상태가 `CLOSE`로 변경되어야 한다.")
    @Test
    void addApplicant() {
        // given
        LocalDate openingDate = LocalDate.now();
        CourseSchedule courseSchedule = new CourseSchedule(1L, openingDate);

        // when
        for (int i = 0; i < 30; i++) {
            courseSchedule.addApplicant();
        }

        // then
        assertThat(courseSchedule.canRegister()).isFalse();
        assertThat(courseSchedule.getStatus()).isEqualTo(CourseStatus.FULL);
    }

    @DisplayName("신청자가 30명 일 때, 추가로 신청하면 예외가 발생해야 한다.")
    @Test
    void addApplicant_exception() {
        // given
        LocalDate openingDate = LocalDate.now();
        CourseSchedule courseSchedule = new CourseSchedule(1L, openingDate);

        // when
        for (int i = 0; i < 30; i++) {
            courseSchedule.addApplicant();
        }

        // then
        assertThat(courseSchedule.canRegister()).isFalse();
        assertThat(courseSchedule.getStatus()).isEqualTo(CourseStatus.FULL);
        assertThatThrownBy(courseSchedule::addApplicant)
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("해당 강좌는 신청이 마감되었습니다.");
    }
}