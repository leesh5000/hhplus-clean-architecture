package kr.co.aerix.hhplus.user.domain.dto.response;

import kr.co.aerix.hhplus.course.domain.Course;
import kr.co.aerix.hhplus.course.domain.CourseSchedule;
import kr.co.aerix.hhplus.user.domain.Registration;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MyCourserInfo(Long id,
                            String name,
                            String speaker,
                            String status,
                            LocalDateTime registeredDateTime,
                            LocalDate openingDate,
                            Integer capacity,
                            Integer currentApplicants) {

    public static MyCourserInfo from(Course course, CourseSchedule courseSchedule, Registration registration) {
        return new MyCourserInfo(
                course.getId(),
                course.getName(),
                course.getSpeaker(),
                courseSchedule.getStatus().name(),
                registration.getRegistrationDateTime(),
                courseSchedule.getOpeningDate(),
                course.getCapacity(),
                courseSchedule.getCurrentApplicants()
        );
    }
}
