package kr.co.aerix.hhplus.course.interfaces.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.co.aerix.hhplus.course.domain.dto.request.CourseCommand;

import java.time.LocalDate;
import java.util.List;

public record OrganizeCourseRequest(String name,
                                    String speaker,
                                    @JsonFormat(pattern = "yyyy-MM-dd")
                            List<LocalDate> openingDates) {

    public CourseCommand toCommand() {
        return new CourseCommand(name, speaker);
    }
}
