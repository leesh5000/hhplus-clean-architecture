package kr.co.aerix.hhplus.course.domain;

import kr.co.aerix.hhplus.course.domain.dto.request.CourseCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {

    @DisplayName("날짜를 입력하여 강좌를 개설할 수 있다.")
    @Test
    void Course_Open_test() {

        // Given
        CourseCommand command = new CourseCommand("자바", "김코딩");
        LocalDate openingDate = LocalDate.now().plusDays(1);
        List<LocalDate> openingDates = List.of(openingDate);
        Course course = new Course(command);

        // When
        List<CourseSchedule> courseSchedules = course.open(openingDates);

        // Then
        assertThat(courseSchedules).hasSize(1);
        assertThat(courseSchedules.getFirst().getOpeningDate()).isEqualTo(openingDate);
        assertThat(courseSchedules.getFirst().getCurrentApplicants()).isEqualTo(0);
    }

    @DisplayName("여러 날짜에 강좌를 개설할 수 있다.")
    @Test
    void Course_Open_test2() {

        // Given
        CourseCommand command = new CourseCommand("자바", "김코딩");
        LocalDate openingDate1 = LocalDate.now().plusDays(1);
        LocalDate openingDate2 = LocalDate.now().plusDays(2);
        List<LocalDate> openingDates = List.of(openingDate1, openingDate2);
        Course course = new Course(command);

        // When
        List<CourseSchedule> courseSchedules = course.open(openingDates);

        // Then
        assertThat(courseSchedules).hasSize(2);
        assertThat(courseSchedules.getFirst().getOpeningDate()).isEqualTo(openingDate1);
        assertThat(courseSchedules.getFirst().getCurrentApplicants()).isEqualTo(0);
        assertThat(courseSchedules.getLast().getOpeningDate()).isEqualTo(openingDate2);
        assertThat(courseSchedules.getLast().getCurrentApplicants()).isEqualTo(0);
    }

    @DisplayName("동일한 날짜를 여러개 입력하면, 한 번만 개설되어야 한다.")
    @Test
    void Course_Open_test3() {

            // Given
            CourseCommand command = new CourseCommand("자바", "김코딩");
            LocalDate openingDate = LocalDate.now().plusDays(1);
            List<LocalDate> openingDates = List.of(openingDate, openingDate);
            Course course = new Course(command);

            // When
            List<CourseSchedule> courseSchedules = course.open(openingDates);

            // Then
            assertThat(courseSchedules).hasSize(1);
            assertThat(courseSchedules.getFirst().getOpeningDate()).isEqualTo(openingDate);
            assertThat(courseSchedules.getFirst().getCurrentApplicants()).isEqualTo(0);
    }

    @DisplayName("날짜를 입력하지 않으면, 빈 배열을 반환한다.")
    @Test
    void Course_Open_test4() {

        // Given
        CourseCommand command = new CourseCommand("자바", "김코딩");
        List<LocalDate> openingDates = List.of();
        Course course = new Course(command);

        // When
        List<CourseSchedule> courseSchedules = course.open(openingDates);

        // Then
        assertThat(courseSchedules).isEmpty();
    }

    @DisplayName("현재 날짜보다 이전 날짜의를 입력하면, 무시된다.")
    @Test
    void Course_Open_test5() {

        // Given
        CourseCommand command = new CourseCommand("자바", "김코딩");
        LocalDate openingDate = LocalDate.now().minusDays(1);
        List<LocalDate> openingDates = List.of(openingDate);
        Course course = new Course(command);

        // When
        List<CourseSchedule> courseSchedules = course.open(openingDates);

        // Then
        assertThat(courseSchedules).isEmpty();
    }

}