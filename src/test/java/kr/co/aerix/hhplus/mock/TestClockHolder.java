package kr.co.aerix.hhplus.mock;

import kr.co.aerix.hhplus.common.application.ClockHolder;

import java.time.LocalDateTime;

public class TestClockHolder implements ClockHolder {

    private final LocalDateTime localDateTime;

    public TestClockHolder(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public LocalDateTime currentDateTime() {
        return localDateTime;
    }
}
