package com.ssafy.keywordbatch.service;

import com.ssafy.keywordbatch.domain.keyword.KeywordLog;
import com.ssafy.keywordbatch.domain.keyword.KeywordLogDocs;
import com.ssafy.keywordbatch.domain.keyword.repository.KeywordLogMongoRepository;
import com.ssafy.keywordbatch.domain.keyword.repository.KeywordLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class KeywordLogService {

    private final KeywordLogRepository keywordLogRepository;
    private final KeywordLogMongoRepository keywordLogMongoRepository;

    public int calcKeywordLog() {
        List<KeywordLogDocs> keywordLogs = keywordLogMongoRepository.findAll();

        Map<Long, Integer> keywordCount = new HashMap<>();

        for (KeywordLogDocs keywordLog : keywordLogs) {
            Long keywordId = keywordLog.getKeywordId();

            int count = keywordCount.getOrDefault(keywordId, 0);

            keywordCount.put(keywordId, count + 1);
        }

        List<KeywordLog> logs = new ArrayList<>();
        for (Long keywordId : keywordCount.keySet()) {
            KeywordLog log = KeywordLog.builder()
                .keywordId(keywordId)
                .count(keywordCount.get(keywordId))
                .build();
            logs.add(log);
        }

        List<KeywordLog> savedLogs = keywordLogRepository.saveAll(logs);

        keywordLogMongoRepository.deleteAll();

        return savedLogs.size();
    }
}
