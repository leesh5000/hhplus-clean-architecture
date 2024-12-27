package kr.co.aerix.hhplus.course.domain.dto.response;

import kr.co.aerix.hhplus.course.domain.CourseSchedule;
import kr.co.aerix.hhplus.course.domain.CourseStatus;

import java.time.LocalDate;

public record CourseOpeningDate(LocalDate openingDate,
                                CourseStatus status,
                                Integer currentApplicants) {

    public static CourseOpeningDate from(CourseSchedule courseSchedule) {
        return new CourseOpeningDate(
                courseSchedule.getOpeningDate(),
                courseSchedule.getStatus(),
                courseSchedule.getCurrentApplicants()
        );
    }
}
