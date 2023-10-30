package com.ssafy.keywordservice.api.service.keywordsearch;

import com.ssafy.keywordservice.api.controller.keywordsearch.response.KeywordSearchResponse;
import com.ssafy.keywordservice.domain.keywordsearch.repository.KeywordSearchQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class KeywordSearchQueryService {

    private final KeywordSearchQueryRepository keywordSearchQueryRepository;

    public List<KeywordSearchResponse> getMyKeywordSearch(String memberKey) {
        return keywordSearchQueryRepository.findTop5ByMemberKey(memberKey);
    }
}
