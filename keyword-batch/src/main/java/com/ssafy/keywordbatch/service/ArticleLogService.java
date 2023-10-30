package com.ssafy.keywordbatch.service;

import com.ssafy.keywordbatch.domain.article.ArticleLog;
import com.ssafy.keywordbatch.domain.article.ArticleLogDocs;
import com.ssafy.keywordbatch.domain.article.repository.ArticleLogMongoRepository;
import com.ssafy.keywordbatch.domain.article.repository.ArticleLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleLogService {

    private final ArticleLogRepository articleLogRepository;
    private final ArticleLogMongoRepository articleLogMongoRepository;

    public int calcArticleLog() {
        List<ArticleLogDocs> articleLogs = articleLogMongoRepository.findAll();

        Map<Long, Integer> articleCount = new HashMap<>();

        for (ArticleLogDocs articleLog : articleLogs) {
            Long articleId = articleLog.getArticleId();

            int count = articleCount.getOrDefault(articleId, 0);

            articleCount.put(articleId, count + 1);
        }

        List<ArticleLog> logs = articleCount.keySet().stream().map(articleId -> ArticleLog.builder()
                .articleId(articleId)
                .count(articleCount.get(articleId))
                .build())
            .collect(Collectors.toList());

        List<ArticleLog> savedLogs = articleLogRepository.saveAll(logs);

        articleLogMongoRepository.deleteAll();

        return savedLogs.size();
    }
}
