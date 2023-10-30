package com.ssafy.recommendationservice.api.controller.hotkeyword.response;

import lombok.Builder;
import lombok.Data;

@Data
public class LiveResponse {

    private Long keywordId;
    private String keyword;

    @Builder
    public LiveResponse(Long keywordId, String keyword) {
        this.keywordId = keywordId;
        this.keyword = keyword;
    }
}
