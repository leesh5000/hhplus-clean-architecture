package kr.co.aerix.hhplus.course.domain.service;

import jakarta.transaction.Transactional;
import kr.co.aerix.hhplus.course.domain.Course;
import kr.co.aerix.hhplus.course.domain.dto.request.CourseCommand;
import kr.co.aerix.hhplus.course.domain.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class OrganizeCourseService {

    private final CourseRepository courseRepository;

    public Long organize(CourseCommand command) {
        Course course = new Course(command);
        return courseRepository.save(course);
    }
}
