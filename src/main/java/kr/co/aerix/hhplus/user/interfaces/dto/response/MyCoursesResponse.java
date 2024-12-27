package kr.co.aerix.hhplus.user.interfaces.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.co.aerix.hhplus.user.domain.dto.response.MyCourserInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MyCoursesResponse(Long courseId,
                                String name,
                                String speaker,
                                String status,
                                @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
                                LocalDateTime registeredDateTime,
                                @JsonFormat(pattern = "yyyy-MM-dd")
                                LocalDate openingDate,
                                Integer capacity,
                                Integer currentApplicants) {
    public static MyCoursesResponse from(MyCourserInfo myCourserInfo) {
        return new MyCoursesResponse(
                myCourserInfo.id(),
                myCourserInfo.name(),
                myCourserInfo.speaker(),
                myCourserInfo.status(),
                myCourserInfo.registeredDateTime(),
                myCourserInfo.openingDate(),
                myCourserInfo.capacity(),
                myCourserInfo.currentApplicants()
        );
    }
}
