package com.ssafy.keywordservice.api.service.keyword;

import com.ssafy.keywordservice.api.controller.client.response.KeywordResponse;
import com.ssafy.keywordservice.api.controller.keyword.response.KeywordQuizClientResponse;
import com.ssafy.keywordservice.client.OpenApiServiceClient;
import com.ssafy.keywordservice.domain.keyword.Keyword;
import com.ssafy.keywordservice.domain.keyword.repository.KeywordQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class KeywordQueryService {

    private final KeywordQueryRepository keywordQueryRepository;
    private final OpenApiServiceClient openApiServiceClient;

    public List<KeywordQuizClientResponse> getKeywordProblem() {
        List<Keyword> answers = keywordQueryRepository.findTop10Keyword();

        log.info("answers.size()={}", answers.size());
        if (answers.size() != 10) {
            throw new IllegalArgumentException("키워드를 가져오지 못했습니다.");
        }
        List<Long> answerIds = answers.stream()
            .map(Keyword::getId)
            .collect(Collectors.toList());

        List<Keyword> keywords = keywordQueryRepository.findByIdNotIn(answerIds);
        log.info("keywords.size()={}", keywords.size());
        if (keywords.size() != 30) {
            throw new IllegalArgumentException("키워드를 가져오지 못했습니다.");
        }
        List<KeywordQuizClientResponse> responses = new ArrayList<>();
        int index = 0;
        for (Keyword answer : answers) {
            List<String> contents = new ArrayList<>();
            contents.add(answer.getWord());

            for (int i = 0; i < 3; i++) {
                Keyword keyword = keywords.get(index++);
                contents.add(keyword.getWord());
            }

            Collections.shuffle(contents);
            String description = openApiServiceClient.searchNaverVocabulary(answer.getWord());

            KeywordQuizClientResponse response = KeywordQuizClientResponse.builder()
                .answer(answer.getWord())
                .description(description)
                .contents(contents)
                .build();
            responses.add(response);
        }

        return responses;
    }

    public List<KeywordResponse> getKeywordByIds(List<Long> keywordIds) {
        return keywordQueryRepository.findKeywordByIdIn(keywordIds);
    }
}
