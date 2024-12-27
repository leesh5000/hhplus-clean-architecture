package kr.co.aerix.hhplus.user.domain.service;

import jakarta.transaction.Transactional;
import kr.co.aerix.hhplus.common.application.ClockHolder;
import kr.co.aerix.hhplus.course.domain.CourseSchedule;
import kr.co.aerix.hhplus.course.domain.repository.CourseScheduleRepository;
import kr.co.aerix.hhplus.user.domain.Registration;
import kr.co.aerix.hhplus.user.domain.User;
import kr.co.aerix.hhplus.user.domain.dto.request.RegisterCommand;
import kr.co.aerix.hhplus.user.domain.repository.RegistrationRepository;
import kr.co.aerix.hhplus.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Transactional
@RequiredArgsConstructor
@Service
public class RegisterCourseService {

    private final UserRepository userRepository;
    private final RegistrationRepository registrationRepository;
    private final CourseScheduleRepository courseScheduleRepository;
    private final UserScheduleManager userScheduleManager;
    private final ClockHolder clockHolder;

    public void registerCourse(RegisterCommand command) {

        Long userId = command.userId();
        Long courseId = command.courseId();
        LocalDate openingDate = command.openingDate();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        CourseSchedule courseSchedule = courseScheduleRepository.findByCourseIdAndOpeningDate(courseId, openingDate)
                .orElseThrow(() -> new IllegalArgumentException("해당 날짜에 개설된 강좌가 없습니다."));

        if (!courseSchedule.canRegister()) {
            throw new IllegalArgumentException("해당 강좌는 신청이 마감되었습니다.");
        }

        Registration registration = user.registerFor(courseSchedule, userScheduleManager, clockHolder);
        registrationRepository.save(registration);
        courseScheduleRepository.save(courseSchedule);
    }

}
