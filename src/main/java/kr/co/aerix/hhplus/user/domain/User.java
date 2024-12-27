package kr.co.aerix.hhplus.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.co.aerix.hhplus.common.application.ClockHolder;
import kr.co.aerix.hhplus.course.domain.CourseSchedule;
import kr.co.aerix.hhplus.user.domain.service.UserScheduleManager;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Entity(name = "users")
public class User {

    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;

    public User(Long id) {
        this.id = id;
    }

    public Registration registerFor(CourseSchedule courseSchedule, UserScheduleManager userScheduleManager, ClockHolder clockHolder) {
        LocalDate openingDate = courseSchedule.getOpeningDate();
        if (!userScheduleManager.isAvailableDate(id, openingDate)) {
            throw new IllegalArgumentException("해당 날짜에 이미 신청한 강좌가 있습니다.");
        }
        courseSchedule.addApplicant();
        return new Registration(id, courseSchedule.getId(), clockHolder.currentDateTime());
    }

    public User copy(long id) {
        User user = new User();
        user.id = id;
        user.name = name;
        return user;
    }
}
