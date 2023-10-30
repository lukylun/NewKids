package com.ssafy.keywordbatch.scheduler;

import com.ssafy.keywordbatch.service.ArticleLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class ArticleScheduler {

    private final ArticleLogService articleLogService;

    @Scheduled(cron = "0 0 * * * ?")
    public void insertArticleLog() {
        log.info("[Article] 뉴스 기사 기록 스케줄러 실행");

        int size = articleLogService.calcArticleLog();

        log.info("[Article] 뉴스 기사 기록 스케줄러 종료={}", size);
    }
}
