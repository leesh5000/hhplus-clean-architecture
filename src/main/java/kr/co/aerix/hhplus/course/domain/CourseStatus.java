package kr.co.aerix.hhplus.course.domain;

public enum CourseStatus {
    OPEN,       // 강좌를 신청할 수 있는 상태
    CLOSE,      // 강좌 신청이 마감된 상태 (정원이 찾거나, 관리자에 의해 강좌가 마감된 경우)
    FULL        // 강좌 정원이 찬 상태
}
