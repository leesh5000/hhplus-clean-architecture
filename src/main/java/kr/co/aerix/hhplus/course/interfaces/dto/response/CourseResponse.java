package kr.co.aerix.hhplus.course.interfaces.dto.response;

import kr.co.aerix.hhplus.course.domain.dto.response.CourseInfo;

import java.util.List;

public record CourseResponse(
        Long id,
        String name,
        String speaker,
        int capacity,
        List<CourseOpeningDateResponse> openingDates) {

    public static CourseResponse from(CourseInfo courseInfo) {
        List<CourseOpeningDateResponse> openingDateResponses = courseInfo.openingDates().stream()
                .map(CourseOpeningDateResponse::from)
                .toList();
        return new CourseResponse(
                courseInfo.id(),
                courseInfo.name(),
                courseInfo.speaker(),
                courseInfo.capacity(),
                openingDateResponses
        );
    }
}

