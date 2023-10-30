package com.ssafy.keywordservice.api.controller.keyword.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatedKeywordRequest {

    private String word;

    @Builder
    private CreatedKeywordRequest(String word) {
        this.word = word;
    }
}
