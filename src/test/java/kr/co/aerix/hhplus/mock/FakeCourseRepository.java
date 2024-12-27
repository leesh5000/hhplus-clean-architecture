package kr.co.aerix.hhplus.mock;

import kr.co.aerix.hhplus.course.domain.Course;
import kr.co.aerix.hhplus.course.domain.repository.CourseRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FakeCourseRepository implements CourseRepository {

    private final AtomicLong autoIncrementedId = new AtomicLong(0);
    private final List<Course> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public Long save(Course course) {
        if (course.getId() != null) {
            data.removeIf(item -> item.getId().equals(course.getId()));
        }
        Course newCourse = course.copy(autoIncrementedId.incrementAndGet());
        data.add(newCourse);
        return newCourse.getId();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return data.stream().filter(item -> item.getId().equals(id)).findAny();
    }

    @Override
    public void deleteAll() {
        data.clear();
    }

    @Override
    public int size() {
        return data.size();
    }
}
