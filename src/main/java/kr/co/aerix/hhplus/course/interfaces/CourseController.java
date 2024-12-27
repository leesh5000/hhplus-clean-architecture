package kr.co.aerix.hhplus.course.interfaces;

import kr.co.aerix.hhplus.course.domain.CourseStatus;
import kr.co.aerix.hhplus.course.domain.dto.request.CourseCommand;
import kr.co.aerix.hhplus.course.domain.dto.request.CourseQuery;
import kr.co.aerix.hhplus.course.domain.dto.response.CourseInfo;
import kr.co.aerix.hhplus.course.domain.service.ListCourseService;
import kr.co.aerix.hhplus.course.domain.service.OpenCourseService;
import kr.co.aerix.hhplus.course.domain.service.OrganizeCourseService;
import kr.co.aerix.hhplus.course.interfaces.dto.request.OpenCourseRequest;
import kr.co.aerix.hhplus.course.interfaces.dto.request.OrganizeCourseRequest;
import kr.co.aerix.hhplus.course.interfaces.dto.response.CourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController("/courses")
public class CourseController {

    private final OrganizeCourseService organizeCourseService;
    private final OpenCourseService openCourseService;
    private final ListCourseService listCourseService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Void> organize(@RequestBody OrganizeCourseRequest request) {
        CourseCommand command = request.toCommand();
        Long courseId = organizeCourseService.organize(command);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(courseId)
                .toUri();
        return ResponseEntity.created(uri)
                .build();
    }

    @PostMapping(path = "/{courseId}/open", consumes = "application/json")
    public ResponseEntity<Void> open(@PathVariable Long courseId,
                                     @RequestBody OpenCourseRequest request) {
        List<LocalDate> openingDates = request.openingDates();
        openCourseService.open(courseId, openingDates);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CourseResponse>> list(@RequestParam(required = true) LocalDate startDate,
                                                     @RequestParam(required = true) LocalDate endDate,
                                                     @RequestParam(required = false, defaultValue = "OPEN") String status) {
        CourseStatus courseStatus = CourseStatus.valueOf(status);
        CourseQuery query = new CourseQuery(startDate, endDate, courseStatus);
        List<CourseInfo> courseInfos = listCourseService.list(query);
        List<CourseResponse> responses = courseInfos.stream()
                .map(CourseResponse::from)
                .toList();
        return ResponseEntity.ok(responses);
    }

}
