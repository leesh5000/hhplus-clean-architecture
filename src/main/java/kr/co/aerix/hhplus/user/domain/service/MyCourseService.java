package kr.co.aerix.hhplus.user.domain.service;

import kr.co.aerix.hhplus.course.domain.Course;
import kr.co.aerix.hhplus.course.domain.CourseSchedule;
import kr.co.aerix.hhplus.course.domain.repository.CourseRepository;
import kr.co.aerix.hhplus.course.domain.repository.CourseScheduleRepository;
import kr.co.aerix.hhplus.user.domain.Registration;
import kr.co.aerix.hhplus.user.domain.dto.response.MyCourserInfo;
import kr.co.aerix.hhplus.user.domain.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MyCourseService {

    private final CourseRepository courseRepository;
    private final CourseScheduleRepository courseScheduleRepository;
    private final RegistrationRepository registrationRepository;

    public List<MyCourserInfo> getMyCourses(Long userId) {

        List<Registration> userRegistrations = registrationRepository.findAllByUserId(userId);
        return userRegistrations.stream().map(registration -> {

            Long courseScheduleId = registration.getCourseScheduleId();
            CourseSchedule courseSchedule = courseScheduleRepository.findById(courseScheduleId)
                    .orElseThrow(() -> new IllegalArgumentException("강좌를 찾을 수 없습니다."));

            Long courseId = courseSchedule.getCourseId();
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new IllegalArgumentException("강좌를 찾을 수 없습니다."));

            return MyCourserInfo.from(course, courseSchedule, registration);
        }).toList();
    }
}
