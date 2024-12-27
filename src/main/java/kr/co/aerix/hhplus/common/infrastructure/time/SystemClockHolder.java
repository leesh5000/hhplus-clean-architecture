package kr.co.aerix.hhplus.common.infrastructure.time;

import kr.co.aerix.hhplus.common.application.ClockHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SystemClockHolder implements ClockHolder {

    @Override
    public LocalDateTime currentDateTime() {
        return LocalDateTime.now();
    }
}
