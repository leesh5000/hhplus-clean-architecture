package kr.co.aerix.hhplus.course.infrastructure.persistence.jpa;

import jakarta.persistence.LockModeType;
import kr.co.aerix.hhplus.course.domain.CourseSchedule;
import kr.co.aerix.hhplus.course.domain.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CourseScheduleJpaRepository extends JpaRepository<CourseSchedule, Long> {
    List<CourseSchedule> findAllByOpeningDateBetweenAndStatus(LocalDate from, LocalDate to, CourseStatus status);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<CourseSchedule> findByCourseIdAndOpeningDate(Long courseId, LocalDate openingDate);
    List<CourseSchedule> findByCourseId(Long courseId);
}
