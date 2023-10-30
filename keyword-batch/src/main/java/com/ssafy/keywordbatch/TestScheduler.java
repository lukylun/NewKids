package com.ssafy.keywordbatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class TestScheduler {

    @Scheduled(cron = "0 0 * * * ?")
    public void testScheduled() {
        log.info("[TEST] Keyword 스케줄러 실행={}", LocalDateTime.now());
    }
}
