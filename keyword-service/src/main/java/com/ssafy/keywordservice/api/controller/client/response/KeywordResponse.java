package com.ssafy.keywordservice.api.controller.client.response;

import lombok.Builder;
import lombok.Data;

@Data
public class KeywordResponse {

    private Long keywordId;
    private String keyword;

    @Builder
    public KeywordResponse(Long keywordId, String keyword) {
        this.keywordId = keywordId;
        this.keyword = keyword;
    }
}
