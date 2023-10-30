package com.ssafy.vocabularyservice.api.controller.vocabulary.response;

import lombok.Builder;
import lombok.Data;

@Data
public class WordClientResponse {

    private String wordKey;
    private String content;
    private String description;

    @Builder
    public WordClientResponse(String wordKey, String content, String description) {
        this.wordKey = wordKey;
        this.content = content;
        this.description = description;
    }
}
