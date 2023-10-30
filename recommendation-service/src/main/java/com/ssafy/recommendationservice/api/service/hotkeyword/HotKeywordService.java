package com.ssafy.recommendationservice.api.service.hotkeyword;

import com.ssafy.recommendationservice.api.controller.hotkeyword.response.LiveResponse;
import com.ssafy.recommendationservice.api.controller.hotkeyword.response.WordCloudResponse;
import com.ssafy.recommendationservice.client.KeywordServiceClient;
import com.ssafy.recommendationservice.client.request.KeywordRequest;
import com.ssafy.recommendationservice.client.response.KeywordResponse;
import com.ssafy.recommendationservice.domain.keyword.KeywordLog;
import com.ssafy.recommendationservice.domain.keyword.repository.KeywordLogQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HotKeywordService {

    private final KeywordLogQueryRepository keywordLogQueryRepository;
    private final KeywordServiceClient keywordServiceClient;

    public List<LiveResponse> getLiveHotKeyword(LocalDateTime targetDateTime) {
        List<Long> keywordIds = keywordLogQueryRepository.findLiveHotKeyword(targetDateTime);

        KeywordRequest request = KeywordRequest.builder()
            .keywordIds(keywordIds)
            .build();

        List<KeywordResponse> responses = keywordServiceClient.getKeywords(request);

        return responses.stream()
            .map(response -> LiveResponse.builder()
                .keywordId(response.getKeywordId())
                .keyword(response.getKeyword())
                .build())
            .collect(Collectors.toList());
    }

    public List<WordCloudResponse> getCloudHotKeyword(LocalDateTime targetDateTime) {

        List<KeywordLog> keywordLogs = keywordLogQueryRepository.findCloudHotKeyword(targetDateTime);

        List<Long> keywordIds = keywordLogs.stream()
            .map(KeywordLog::getKeywordId)
            .collect(Collectors.toList());

        KeywordRequest request = KeywordRequest.builder()
            .keywordIds(keywordIds)
            .build();

        List<KeywordResponse> responses = keywordServiceClient.getKeywords(request);

        Map<Long, KeywordResponse> keywordMap = responses.stream()
            .collect(Collectors.toMap(KeywordResponse::getKeywordId, k -> k));

        List<WordCloudResponse> results = new ArrayList<>();
        for (KeywordLog keywordLog : keywordLogs) {
            KeywordResponse content = keywordMap.get(keywordLog.getKeywordId());
            WordCloudResponse.builder()
                .keywordId(content.getKeywordId())
                .text(content.getKeyword())
                .value(keywordLog.getCount())
                .build();
        }

        return results;
    }
}
