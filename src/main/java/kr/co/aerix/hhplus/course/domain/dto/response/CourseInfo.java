package kr.co.aerix.hhplus.course.domain.dto.response;

import kr.co.aerix.hhplus.course.domain.Course;
import kr.co.aerix.hhplus.course.domain.CourseSchedule;

import java.util.List;

public record CourseInfo(Long id,
                         String name,
                         String speaker,
                         Integer capacity,
                         List<CourseOpeningDate> openingDates) {

    public static CourseInfo from(Course course, List<CourseSchedule> courseSchedules) {

        List<CourseOpeningDate> courseOpeningDates = courseSchedules.stream()
                .map(CourseOpeningDate::from)
                .toList();

        return new CourseInfo(
                course.getId(),
                course.getName(),
                course.getSpeaker(),
                course.getCapacity(),
                courseOpeningDates
        );
    }
}
