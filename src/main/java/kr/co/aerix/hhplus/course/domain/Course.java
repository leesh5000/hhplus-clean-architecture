package kr.co.aerix.hhplus.course.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.co.aerix.hhplus.course.domain.dto.request.CourseCommand;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String speaker;
    private Integer capacity;

    public Course(CourseCommand command) {
        this.name = command.name();
        this.speaker = command.speaker();
    }

    public List<CourseSchedule> open(List<LocalDate> openingDates) {
        if (openingDates.isEmpty()) {
            return List.of();
        }
        return openingDates.stream()
                .filter(localDate -> localDate.isAfter(LocalDate.now()))
                .distinct()
                .map(localDate -> new CourseSchedule(this.id, localDate))
                .toList();
    }

    public Course copy(Long id) {
        Course course = new Course();
        course.id = id;
        course.name = this.name;
        course.speaker = this.speaker;
        course.capacity = this.capacity;
        return course;
    }
}
