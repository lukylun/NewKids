package com.ssafy.analysisservice.service;

import com.ssafy.analysisservice.domain.KeywordLog;
import com.ssafy.analysisservice.repository.KeywordLogMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class KeywordLogQueryService {

    private final KeywordLogMongoRepository keywordLogMongoRepository;

    public void none(LocalDateTime from, LocalDateTime to) {
        Map<Long, Integer> map = new HashMap<>();

        List<KeywordLog> keywordLogs = keywordLogMongoRepository.findByCreatedDateBetween(from, to);

        for (KeywordLog log : keywordLogs) {
            Long keywordId = log.getKeywordId();
            int count = map.getOrDefault(keywordId, 0);
            map.put(keywordId, count + 1);
        }

        List<KeywordSortDto> keywordSortDtos = map.keySet().stream()
            .map(keywordId -> KeywordSortDto.builder()
                .keywordId(keywordId)
                .count(map.get(keywordId))
                .build())
            .sorted()
            .collect(Collectors.toList());

        if (keywordSortDtos.size() < 10) {
            return;
        }

        List<KeywordSortDto> content = keywordSortDtos.subList(0, 10);
    }

}
