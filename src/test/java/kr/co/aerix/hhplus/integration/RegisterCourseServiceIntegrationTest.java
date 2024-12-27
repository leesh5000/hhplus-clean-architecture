package kr.co.aerix.hhplus.integration;

import kr.co.aerix.hhplus.course.domain.CourseSchedule;
import kr.co.aerix.hhplus.course.domain.repository.CourseScheduleRepository;
import kr.co.aerix.hhplus.user.domain.Registration;
import kr.co.aerix.hhplus.user.domain.dto.request.RegisterCommand;
import kr.co.aerix.hhplus.user.domain.repository.RegistrationRepository;
import kr.co.aerix.hhplus.user.domain.service.RegisterCourseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SqlGroup({
        @Sql(value = "/sql/register-course-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
@SpringBootTest
public class RegisterCourseServiceIntegrationTest {

    private final CourseScheduleRepository courseScheduleRepository;
    private final RegistrationRepository registrationRepository;
    private final RegisterCourseService registerCourseService;

    public RegisterCourseServiceIntegrationTest(
            @Autowired CourseScheduleRepository courseScheduleRepository,
            @Autowired RegistrationRepository registrationRepository,
            @Autowired RegisterCourseService registerCourseService) {
        this.courseScheduleRepository = courseScheduleRepository;
        this.registrationRepository = registrationRepository;
        this.registerCourseService = registerCourseService;
    }

    @DisplayName("동시에 40명이 특강을 신청하면, 30명까지만 성공해야 한다.")
    @Test
    void open_course_test2() throws InterruptedException {

        // given : /sql/register-course-test-data.sql 참고
        Long courseId = 1L;
        LocalDate openingDate = LocalDate.of(2021, 8, 1);

        // when: 40명의 유저가 동시에 강좌를 신청하면,
        ExecutorService executorService = Executors.newFixedThreadPool(40);
        List<Future<Void>> futures = new ArrayList<>();

        for (int i = 1; i <= 40; i++) {
            Long userId = (long) i;
            RegisterCommand registerCommand = new RegisterCommand(userId, courseId, openingDate);
            futures.add(executorService.submit(() -> {
                try {
                    registerCourseService.registerCourse(registerCommand);
                } catch (IllegalArgumentException e) {
                    // Expected exception for users beyond the 30th
                }
                return null;
            }));
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        // Then: 해당 CourseID 로 등록된 신청자 수는 30명이어야 한다.
        CourseSchedule courseSchedule = courseScheduleRepository.findById(courseId).orElse(null);
        int registeredCount = courseSchedule.getCurrentApplicants();
        assertEquals(30, registeredCount);
    }

}
