package kr.co.aerix.hhplus.course.domain.repository;

import kr.co.aerix.hhplus.course.domain.CourseSchedule;
import kr.co.aerix.hhplus.course.domain.CourseStatus;
import kr.co.aerix.hhplus.course.domain.dto.response.CourseInfo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CourseScheduleRepository {
    void saveAll(List<CourseSchedule> courseSchedules);
    List<CourseInfo> findAllByOpeningDateBetweenAndStatus(LocalDate from, LocalDate to, CourseStatus status);
    Optional<CourseSchedule> findByCourseIdAndOpeningDate(Long courseId, LocalDate openingDate);
    List<CourseSchedule> findAllByIds(List<Long> ids);
    void save(CourseSchedule courseSchedule);
    Optional<CourseSchedule> findById(Long courseScheduleId);
    List<CourseSchedule> findByCourseId(Long courseId);
    void deleteAll();
}
