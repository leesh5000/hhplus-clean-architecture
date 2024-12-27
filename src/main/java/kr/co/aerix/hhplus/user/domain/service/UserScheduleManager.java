package kr.co.aerix.hhplus.user.domain.service;

import kr.co.aerix.hhplus.course.domain.CourseSchedule;
import kr.co.aerix.hhplus.course.domain.repository.CourseScheduleRepository;
import kr.co.aerix.hhplus.user.domain.Registration;
import kr.co.aerix.hhplus.user.domain.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserScheduleManager {

    private final RegistrationRepository registrationRepository;
    private final CourseScheduleRepository courseScheduleRepository;

    public boolean isAvailableDate(Long userId, LocalDate openingDate) {

        List<Registration> userRegistrations = registrationRepository.findAllByUserId(userId);
        List<Long> userCourseScheduleIds = userRegistrations.stream()
                .map(Registration::getCourseScheduleId)
                .toList();
        List<CourseSchedule> userCourseSchedules = courseScheduleRepository.findAllByIds(userCourseScheduleIds);

        boolean isAlreadyRegistered = userCourseSchedules.stream()
                .anyMatch(courseSchedule -> courseSchedule.getOpeningDate().equals(openingDate));

        return !isAlreadyRegistered;
    }
}
