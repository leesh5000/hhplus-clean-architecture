package kr.co.aerix.hhplus.user.interfaces.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.co.aerix.hhplus.user.domain.dto.request.RegisterCommand;

import java.time.LocalDate;

public record RegisterCourseRequest(Long courseId,
                                    @JsonFormat(pattern = "yyyy-MM-dd")
                                    LocalDate openingDate) {

    public RegisterCommand toCommand(Long userId) {
        return new RegisterCommand(userId, courseId, openingDate);
    }
}
