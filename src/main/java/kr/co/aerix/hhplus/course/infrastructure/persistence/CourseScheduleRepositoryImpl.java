package kr.co.aerix.hhplus.course.infrastructure.persistence;

import kr.co.aerix.hhplus.course.domain.Course;
import kr.co.aerix.hhplus.course.domain.CourseSchedule;
import kr.co.aerix.hhplus.course.domain.CourseStatus;
import kr.co.aerix.hhplus.course.domain.dto.response.CourseInfo;
import kr.co.aerix.hhplus.course.domain.repository.CourseRepository;
import kr.co.aerix.hhplus.course.domain.repository.CourseScheduleRepository;
import kr.co.aerix.hhplus.course.infrastructure.persistence.jpa.CourseScheduleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class CourseScheduleRepositoryImpl implements CourseScheduleRepository {

    private final CourseScheduleJpaRepository courseScheduleJpaRepository;
    private final CourseRepository courseRepository;

    @Override
    public void saveAll(List<CourseSchedule> courseSchedules) {
        courseScheduleJpaRepository.saveAll(courseSchedules);
    }

    @Override
    public List<CourseInfo> findAllByOpeningDateBetweenAndStatus(LocalDate from, LocalDate to, CourseStatus status) {
        return courseScheduleJpaRepository.findAllByOpeningDateBetweenAndStatus(from, to, status)
                .stream()
                .collect(Collectors.groupingBy(CourseSchedule::getCourseId))
                .entrySet()
                .stream()
                .map(entry -> {
                    Long courseId = entry.getKey();
                    List<CourseSchedule> schedules = entry.getValue();
                    Course course = courseRepository.findById(courseId).orElseThrow();
                    return CourseInfo.from(course, schedules);
                })
                .toList();

    }

    @Override
    public Optional<CourseSchedule> findByCourseIdAndOpeningDate(Long courseId, LocalDate openingDate) {
        return courseScheduleJpaRepository.findByCourseIdAndOpeningDate(courseId, openingDate);
    }

    @Override
    public List<CourseSchedule> findAllByIds(List<Long> ids) {
        return courseScheduleJpaRepository.findAllById(ids);
    }

    @Override
    public void save(CourseSchedule courseSchedule) {
        courseScheduleJpaRepository.save(courseSchedule);
    }

    @Override
    public Optional<CourseSchedule> findById(Long courseScheduleId) {
        return courseScheduleJpaRepository.findById(courseScheduleId);
    }

    @Override
    public List<CourseSchedule> findByCourseId(Long courseId) {
        return courseScheduleJpaRepository.findByCourseId(courseId);
    }

    @Override
    public void deleteAll() {
        courseScheduleJpaRepository.deleteAll();
    }
}
