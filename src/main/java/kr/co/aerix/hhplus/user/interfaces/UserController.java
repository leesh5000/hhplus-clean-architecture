package kr.co.aerix.hhplus.user.interfaces;

import kr.co.aerix.hhplus.user.domain.dto.request.RegisterCommand;
import kr.co.aerix.hhplus.user.domain.dto.response.MyCourserInfo;
import kr.co.aerix.hhplus.user.domain.service.MyCourseService;
import kr.co.aerix.hhplus.user.domain.service.RegisterCourseService;
import kr.co.aerix.hhplus.user.interfaces.dto.request.RegisterCourseRequest;
import kr.co.aerix.hhplus.user.interfaces.dto.response.MyCoursesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController("/users")
public class UserController {

    private final MyCourseService myCourseService;
    private final RegisterCourseService registerCourseService;

    @GetMapping(path = "/{userId}/courses", produces = "application/json")
    public ResponseEntity<List<MyCoursesResponse>> getMyCourses(@PathVariable Long userId) {
        List<MyCourserInfo> myCourses = myCourseService.getMyCourses(userId);
        List<MyCoursesResponse> responses = myCourses.stream().map(
                MyCoursesResponse::from
        ).toList();
        return ResponseEntity.ok(responses);
    }

    @PostMapping(path = "/{userId}/courses", produces = "application/json")
    public ResponseEntity<Void> registerCourse(@PathVariable Long userId, RegisterCourseRequest request) {
        RegisterCommand command = request.toCommand(userId);
        registerCourseService.registerCourse(command);
        return ResponseEntity.noContent().build();
    }
}
