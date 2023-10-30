package com.ssafy.vocabularyservice.api.controller.vocabulary.response;

import lombok.Builder;
import lombok.Data;

@Data
public class CheckVocabularyResponse {

    private long checkedCount;
    private long totalCount;

    @Builder
    public CheckVocabularyResponse(long checkedCount, long totalCount) {
        this.checkedCount = checkedCount;
        this.totalCount = totalCount;
    }
}
