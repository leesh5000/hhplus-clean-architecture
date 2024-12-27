package kr.co.aerix.hhplus.course.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CourseSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CourseStatus status = CourseStatus.OPEN;
    private Integer currentApplicants = 0;
    private LocalDate openingDate;
    private Long courseId;

    public CourseSchedule(Long courseId, LocalDate openingDate) {
        this.courseId = courseId;
        this.openingDate = openingDate;
    }

    public boolean canRegister() {
        return status == CourseStatus.OPEN && currentApplicants < 30;
    }

    public void addApplicant() {
        if (!canRegister()) {
            throw new IllegalStateException("해당 강좌는 신청이 마감되었습니다.");
        }
        currentApplicants++;
        if (currentApplicants == 30) {
            status = CourseStatus.FULL;
        }
    }

    public CourseSchedule copy(long id) {
        CourseSchedule courseSchedule = new CourseSchedule(this.courseId, this.openingDate);
        courseSchedule.id = id;
        courseSchedule.status = this.status;
        courseSchedule.currentApplicants = this.currentApplicants;
        return courseSchedule;
    }
}
