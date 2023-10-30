package com.ssafy.recommendationservice.api.controller.hotkeyword.response;

import lombok.Builder;
import lombok.Data;

@Data
public class WordCloudResponse {

    private Long keywordId;
    private String text;
    private int value;

    @Builder
    public WordCloudResponse(Long keywordId, String text, int value) {
        this.keywordId = keywordId;
        this.text = text;
        this.value = value;
    }
}
