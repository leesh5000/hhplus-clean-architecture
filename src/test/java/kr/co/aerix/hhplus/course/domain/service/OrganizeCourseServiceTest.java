package kr.co.aerix.hhplus.course.domain.service;

import kr.co.aerix.hhplus.course.domain.dto.request.CourseCommand;
import kr.co.aerix.hhplus.course.domain.repository.CourseRepository;
import kr.co.aerix.hhplus.mock.FakeCourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrganizeCourseServiceTest {

    @DisplayName("CourseCommand를 입력받아 강좌를 생성할 수 있다.")
    @Test
    void organize_course_test_1() {
        // given
        CourseRepository courseRepository = new FakeCourseRepository();
        OrganizeCourseService sut = new OrganizeCourseService(courseRepository);

        // when
        CourseCommand command = new CourseCommand("자바 프로그래밍", "김영한");
        Long courseId = sut.organize(command);

        // then
        Assertions.assertEquals(1L, courseId);
        Assertions.assertEquals(1, courseRepository.size());
    }


}