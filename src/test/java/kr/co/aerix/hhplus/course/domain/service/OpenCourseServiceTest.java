package kr.co.aerix.hhplus.course.domain.service;

import kr.co.aerix.hhplus.course.domain.Course;
import kr.co.aerix.hhplus.course.domain.CourseSchedule;
import kr.co.aerix.hhplus.course.domain.dto.request.CourseCommand;
import kr.co.aerix.hhplus.course.domain.repository.CourseRepository;
import kr.co.aerix.hhplus.course.domain.repository.CourseScheduleRepository;
import kr.co.aerix.hhplus.mock.FakeCourseRepository;
import kr.co.aerix.hhplus.mock.FakeCourseScheduleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class OpenCourseServiceTest {

    @DisplayName("courseId와 강좌 개설 날짜를 입력받아 강좌를 개설할 수 있다.")
    @Test
    void open_course_test_1() {

        // given
        CourseRepository courseRepository = new FakeCourseRepository();
        CourseCommand command = new CourseCommand("자바 프로그래밍", "강연자");
        Course course = new Course(command);
        Long courseId = courseRepository.save(course);

        CourseScheduleRepository courseScheduleRepository = new FakeCourseScheduleRepository();
        OpenCourseService sut = new OpenCourseService(
                courseRepository,
                courseScheduleRepository
        );

        // when
        sut.open(courseId, List.of(
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(3)
        ));

        // then
        List<CourseSchedule> courseSchedules = courseScheduleRepository.findByCourseId(courseId);
        Assertions.assertThat(courseSchedules).hasSize(3);
    }

    @DisplayName("빈 날짜를 입력하면, 개설된 강좌가 없어야 한다.")
    @Test
    void open_course_test_2() {

        // given
        CourseRepository courseRepository = new FakeCourseRepository();
        CourseCommand command = new CourseCommand("자바 프로그래밍", "강연자");
        Course course = new Course(command);
        Long courseId = courseRepository.save(course);

        CourseScheduleRepository courseScheduleRepository = new FakeCourseScheduleRepository();
        OpenCourseService sut = new OpenCourseService(
                courseRepository,
                courseScheduleRepository
        );

        // when
        sut.open(courseId, List.of());

        // then
        List<CourseSchedule> courseSchedules = courseScheduleRepository.findByCourseId(courseId);
        Assertions.assertThat(courseSchedules).isEmpty();
    }


}