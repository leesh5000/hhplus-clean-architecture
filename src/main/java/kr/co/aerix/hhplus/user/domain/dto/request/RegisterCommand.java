package kr.co.aerix.hhplus.user.domain.dto.request;

import java.time.LocalDate;

public record RegisterCommand(Long userId, Long courseId, LocalDate openingDate) {
}
