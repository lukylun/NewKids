package com.ssafy.keywordbatch.scheduler;

import com.ssafy.keywordbatch.service.KeywordLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class KeywordScheduler {

    private final KeywordLogService keywordLogService;

    @Scheduled(cron = "0 0 * * * ?")
    public void insertKeywordLog() {
        log.info("[Keyword] 키워드 기록 스케줄러 실행");

        int size = keywordLogService.calcKeywordLog();

        log.info("[Keyword] 키워드 기록 스케줄러 종료={}", size);
    }
}
