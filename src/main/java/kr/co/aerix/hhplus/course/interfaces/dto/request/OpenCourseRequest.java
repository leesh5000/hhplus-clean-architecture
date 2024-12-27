package kr.co.aerix.hhplus.course.interfaces.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public record OpenCourseRequest(
        @JsonFormat(pattern = "yyyy-MM-dd")
        List<LocalDate> openingDates) {
}
