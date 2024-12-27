package kr.co.aerix.hhplus.mock;

import kr.co.aerix.hhplus.course.domain.CourseSchedule;
import kr.co.aerix.hhplus.course.domain.CourseStatus;
import kr.co.aerix.hhplus.course.domain.dto.response.CourseInfo;
import kr.co.aerix.hhplus.course.domain.repository.CourseScheduleRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FakeCourseScheduleRepository implements CourseScheduleRepository {

    private final AtomicLong autoIncrementedId = new AtomicLong(0);
    private final List<CourseSchedule> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void saveAll(List<CourseSchedule> courseSchedules) {
        courseSchedules.forEach(this::save);
    }

    @Override
    public List<CourseInfo> findAllByOpeningDateBetweenAndStatus(LocalDate from, LocalDate to, CourseStatus status) {
        return List.of();
    }

    @Override
    public Optional<CourseSchedule> findByCourseIdAndOpeningDate(Long courseId, LocalDate openingDate) {
        return data.stream()
                .filter(item -> item.getCourseId().equals(courseId))
                .filter(item -> item.getOpeningDate().equals(openingDate))
                .findAny();
    }

    @Override
    public List<CourseSchedule> findAllByIds(List<Long> ids) {
        return data.stream()
                .filter(item -> ids.contains(item.getId()))
                .toList();
    }

    @Override
    public void save(CourseSchedule courseSchedule) {
        if (courseSchedule.getId() != null) {
            data.removeIf(item -> item.getId().equals(courseSchedule.getId()));
        }
        CourseSchedule newCourseSchedule = courseSchedule.copy(autoIncrementedId.incrementAndGet());
        data.add(newCourseSchedule);
    }

    @Override
    public Optional<CourseSchedule> findById(Long courseScheduleId) {
        return data.stream()
                .filter(item -> item.getId().equals(courseScheduleId))
                .findAny();
    }

    @Override
    public List<CourseSchedule> findByCourseId(Long courseId) {
        return data.stream()
                .filter(item -> item.getCourseId().equals(courseId))
                .toList();
    }

    @Override
    public void deleteAll() {

    }
}
