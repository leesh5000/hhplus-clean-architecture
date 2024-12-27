package kr.co.aerix.hhplus.course.infrastructure.persistence.jpa;

import kr.co.aerix.hhplus.course.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseJpaRepository extends JpaRepository<Course, Long> {
}
