package com.ssafy.keywordservice.api.service.popularkeyword;

import com.ssafy.keywordservice.api.controller.popularkeyword.response.PopularKeywordResponse;
import com.ssafy.keywordservice.domain.keywordsearch.repository.KeywordSearchQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PopularKeywordQueryService {

    private final KeywordSearchQueryRepository keywordSearchQueryRepository;

    public List<PopularKeywordResponse> getTopTenPopularKeyword() {
        return keywordSearchQueryRepository.findTop10();
    }
}
