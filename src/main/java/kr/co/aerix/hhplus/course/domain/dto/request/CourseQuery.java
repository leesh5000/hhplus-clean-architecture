package kr.co.aerix.hhplus.course.domain.dto.request;

import kr.co.aerix.hhplus.course.domain.CourseStatus;

import java.time.LocalDate;

public record CourseQuery(LocalDate from,
                          LocalDate to,
                          CourseStatus status) {
}
