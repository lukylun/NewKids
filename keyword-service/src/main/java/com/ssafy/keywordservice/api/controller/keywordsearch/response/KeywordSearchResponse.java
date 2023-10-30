package com.ssafy.keywordservice.api.controller.keywordsearch.response;

import lombok.Builder;
import lombok.Data;

@Data
public class KeywordSearchResponse {

    private Long keywordId;
    private String keyword;
    private int count;

    @Builder
    public KeywordSearchResponse(Long keywordId, String keyword, int count) {
        this.keywordId = keywordId;
        this.keyword = keyword;
        this.count = count;
    }
}
