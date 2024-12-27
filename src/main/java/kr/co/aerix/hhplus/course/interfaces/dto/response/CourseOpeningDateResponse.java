package kr.co.aerix.hhplus.course.interfaces.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.co.aerix.hhplus.course.domain.dto.response.CourseOpeningDate;

import java.time.LocalDate;

public record CourseOpeningDateResponse(
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate openingDate,
        String status,
        Integer currentApplicants) {

    public static CourseOpeningDateResponse from(CourseOpeningDate courseOpeningDate) {
        return new CourseOpeningDateResponse(
                courseOpeningDate.openingDate(),
                courseOpeningDate.status().name(),
                courseOpeningDate.currentApplicants()
        );
    }
}
