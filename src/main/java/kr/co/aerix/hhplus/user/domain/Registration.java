package kr.co.aerix.hhplus.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Entity
public class Registration {

    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long courseScheduleId;
    private LocalDateTime registrationDateTime;

    public Registration(Long userId, Long courseScheduleId, LocalDateTime registrationDateTime) {
        this.userId = userId;
        this.courseScheduleId = courseScheduleId;
        this.registrationDateTime = registrationDateTime;
    }

    public Registration copy(long id) {
        Registration registration = new Registration();
        registration.id = id;
        registration.userId = userId;
        registration.courseScheduleId = courseScheduleId;
        registration.registrationDateTime = registrationDateTime;
        return registration;
    }
}
