package kr.co.aerix.hhplus.user.domain.service;

import kr.co.aerix.hhplus.common.application.ClockHolder;
import kr.co.aerix.hhplus.course.domain.CourseSchedule;
import kr.co.aerix.hhplus.course.domain.repository.CourseScheduleRepository;
import kr.co.aerix.hhplus.mock.FakeCourseScheduleRepository;
import kr.co.aerix.hhplus.mock.FakeRegistrationRepository;
import kr.co.aerix.hhplus.mock.FakeUserRepository;
import kr.co.aerix.hhplus.mock.TestClockHolder;
import kr.co.aerix.hhplus.user.domain.Registration;
import kr.co.aerix.hhplus.user.domain.User;
import kr.co.aerix.hhplus.user.domain.dto.request.RegisterCommand;
import kr.co.aerix.hhplus.user.domain.repository.RegistrationRepository;
import kr.co.aerix.hhplus.user.domain.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

class RegisterCourseServiceTest {

    @DisplayName("RegisterCommand를 입력 받아 사용자가 강좌를 신청할 수 있다.")
    @Test
    void registerCourse() {

        // given
        User user = new User(1L);
        UserRepository userRepository = new FakeUserRepository();
        userRepository.save(user);

        CourseScheduleRepository courseScheduleRepository = new FakeCourseScheduleRepository();
        LocalDate openingDate = LocalDate.now().plusDays(1);
        CourseSchedule courseSchedule = new CourseSchedule(1L, openingDate);
        courseScheduleRepository.save(courseSchedule);

        RegistrationRepository registrationRepository = new FakeRegistrationRepository();
        UserScheduleManager userScheduleManager = new UserScheduleManager(registrationRepository, courseScheduleRepository);

        LocalDateTime now = LocalDateTime.now();
        ClockHolder clockHolder = new TestClockHolder(now);

        RegisterCourseService sut = new RegisterCourseService(
                userRepository,
                registrationRepository,
                courseScheduleRepository,
                userScheduleManager,
                clockHolder);

        // when
        RegisterCommand command = new RegisterCommand(user.getId(), 1L, openingDate);
        sut.registerCourse(command);

        // then
        List<Registration> userRegistrations = registrationRepository.findAllByUserId(1L);
        Assertions.assertThat(userRegistrations).hasSize(1);
    }

    @DisplayName("요청한 날짜에 개설된 특강이 없으면, 특강 신청이 실패한다.")
    @Test
    void registerCourse_Fail_If_CourseSchedule_Not_Found() {

        // given
        User user = new User(1L);
        UserRepository userRepository = new FakeUserRepository();
        userRepository.save(user);

        CourseScheduleRepository courseScheduleRepository = new FakeCourseScheduleRepository();
        LocalDate openingDate = LocalDate.now().plusDays(1);
        CourseSchedule courseSchedule = new CourseSchedule(1L, openingDate);
        courseScheduleRepository.save(courseSchedule);

        RegistrationRepository registrationRepository = new FakeRegistrationRepository();
        UserScheduleManager userScheduleManager = new UserScheduleManager(registrationRepository, courseScheduleRepository);

        LocalDateTime now = LocalDateTime.now();
        ClockHolder clockHolder = new TestClockHolder(now);

        RegisterCourseService sut = new RegisterCourseService(
                userRepository,
                registrationRepository,
                courseScheduleRepository,
                userScheduleManager,
                clockHolder);

        // when
        LocalDate differentDate = LocalDate.now().plusDays(2);
        RegisterCommand command = new RegisterCommand(user.getId(), 1L, differentDate);
        IllegalArgumentException exception = Assertions.catchThrowableOfType(() -> sut.registerCourse(command), IllegalArgumentException.class);

        // then
        Assertions.assertThat(exception).isNotNull();
    }


}