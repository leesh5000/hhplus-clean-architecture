package kr.co.aerix.hhplus.user.domain;

import kr.co.aerix.hhplus.common.application.ClockHolder;
import kr.co.aerix.hhplus.course.domain.CourseSchedule;
import kr.co.aerix.hhplus.course.domain.repository.CourseScheduleRepository;
import kr.co.aerix.hhplus.mock.FakeCourseScheduleRepository;
import kr.co.aerix.hhplus.mock.FakeRegistrationRepository;
import kr.co.aerix.hhplus.mock.TestClockHolder;
import kr.co.aerix.hhplus.user.domain.repository.RegistrationRepository;
import kr.co.aerix.hhplus.user.domain.service.UserScheduleManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class UserTest {

    @DisplayName("유저는 강좌를 신청하면, 신청 정보를 반환한다.")
    @Test
    void registerFor() {
        // given
        User user = new User();
        Long courseId = 1L;
        LocalDate openingDate = LocalDate.now().plusDays(1);
        CourseSchedule courseSchedule = new CourseSchedule(1L, openingDate);
        RegistrationRepository registrationRepository = new FakeRegistrationRepository();
        CourseScheduleRepository courseScheduleRepository = new FakeCourseScheduleRepository();
        UserScheduleManager userScheduleManager = new UserScheduleManager(registrationRepository, courseScheduleRepository);
        LocalDateTime now = LocalDateTime.now();
        ClockHolder clockHolder = new TestClockHolder(now);

        // when
        Registration registration = user.registerFor(courseSchedule, userScheduleManager, clockHolder);

        // then
        assertThat(registration.getUserId()).isEqualTo(user.getId());
        assertThat(registration.getCourseScheduleId()).isEqualTo(courseSchedule.getId());
        assertThat(registration.getRegistrationDateTime()).isEqualTo(clockHolder.currentDateTime());
    }

    @DisplayName("동일한 날짜에 이미 신청한 강좌가 있으면, 예외를 발생시킨다.")
    @Test
    void registerForWithAlreadyRegistered() {

        // given
        User user = new User(1L);
        Long courseId = 1L;

        LocalDate openingDate = LocalDate.now().plusDays(1);
        CourseSchedule testCourseSchedule = new CourseSchedule(courseId, openingDate);
        CourseScheduleRepository courseScheduleRepository = new FakeCourseScheduleRepository();
        courseScheduleRepository.save(testCourseSchedule);

        RegistrationRepository registrationRepository = new FakeRegistrationRepository();
        LocalDateTime now = LocalDateTime.now();
        Registration registration = new Registration(1L, 1L, now);
        registrationRepository.save(registration);

        // when & then
        CourseSchedule courseSchedule = courseScheduleRepository.findById(1L).orElseThrow();
        ClockHolder clockHolder = new TestClockHolder(now);
        UserScheduleManager userScheduleManager = new UserScheduleManager(registrationRepository, courseScheduleRepository);
        assertThatThrownBy(() -> user.registerFor(courseSchedule, userScheduleManager, clockHolder))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 날짜에 이미 신청한 강좌가 있습니다.");
    }


}