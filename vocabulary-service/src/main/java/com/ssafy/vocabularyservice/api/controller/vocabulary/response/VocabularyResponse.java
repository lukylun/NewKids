package com.ssafy.vocabularyservice.api.controller.vocabulary.response;

import lombok.Builder;
import lombok.Data;

@Data
public class VocabularyResponse {

    private Long vocabularyId;
    private String wordKey;
    private String content;
    private String description;
    private boolean isChecked;

    @Builder
    public VocabularyResponse(Long vocabularyId, String wordKey, String content, String description, boolean isChecked) {
        this.vocabularyId = vocabularyId;
        this.wordKey = wordKey;
        this.content = content;
        this.description = description;
        this.isChecked = isChecked;
    }
}
