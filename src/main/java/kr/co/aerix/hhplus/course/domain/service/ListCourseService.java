package kr.co.aerix.hhplus.course.domain.service;

import jakarta.transaction.Transactional;
import kr.co.aerix.hhplus.course.domain.dto.request.CourseQuery;
import kr.co.aerix.hhplus.course.domain.dto.response.CourseInfo;
import kr.co.aerix.hhplus.course.domain.repository.CourseScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ListCourseService {

    private final CourseScheduleRepository courseScheduleRepository;

    public List<CourseInfo> list(CourseQuery query) {
        return courseScheduleRepository.findAllByOpeningDateBetweenAndStatus(
                query.from(),
                query.to(),
                query.status()
        );
    }

}
