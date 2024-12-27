package kr.co.aerix.hhplus.course.infrastructure.persistence;

import kr.co.aerix.hhplus.course.domain.Course;
import kr.co.aerix.hhplus.course.domain.repository.CourseRepository;
import kr.co.aerix.hhplus.course.infrastructure.persistence.jpa.CourseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final CourseJpaRepository courseJpaRepository;

    @Override
    public Long save(Course course) {
        return courseJpaRepository.save(course)
                .getId();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseJpaRepository.findById(id);
    }

    @Override
    public void deleteAll() {
        courseJpaRepository.deleteAll();
    }

    @Override
    public int size() {
        return (int) courseJpaRepository.count();
    }
}
