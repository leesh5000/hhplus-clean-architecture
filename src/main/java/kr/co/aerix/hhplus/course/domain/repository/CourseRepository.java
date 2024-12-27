package kr.co.aerix.hhplus.course.domain.repository;

import kr.co.aerix.hhplus.course.domain.Course;

import java.util.Optional;

public interface CourseRepository {
    Long save(Course course);
    Optional<Course> findById(Long id);
    void deleteAll();
    int size();
}
