package kr.co.aerix.hhplus.course.domain.service;

import jakarta.transaction.Transactional;
import kr.co.aerix.hhplus.course.domain.Course;
import kr.co.aerix.hhplus.course.domain.CourseSchedule;
import kr.co.aerix.hhplus.course.domain.repository.CourseRepository;
import kr.co.aerix.hhplus.course.domain.repository.CourseScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class OpenCourseService {

    private final CourseRepository courseRepository;
    private final CourseScheduleRepository courseScheduleRepository;

    public void open(Long courseId, List<LocalDate> openingDates) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        List<CourseSchedule> courseSchedules = course.open(openingDates);
        courseScheduleRepository.saveAll(courseSchedules);
    }
}
