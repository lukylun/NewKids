package com.ssafy.vocabularyservice.api.controller.vocabulary.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CreateVocabularyRequest {

    @NotBlank(message = "단어 키는 필수입니다.")
    private String wordKey;

    @Builder
    private CreateVocabularyRequest(String wordKey) {
        this.wordKey = wordKey;
    }
}
