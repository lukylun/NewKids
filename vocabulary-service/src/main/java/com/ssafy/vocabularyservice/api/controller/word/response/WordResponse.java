package com.ssafy.vocabularyservice.api.controller.word.response;

import com.ssafy.vocabularyservice.domain.word.Word;
import lombok.Builder;
import lombok.Data;

@Data
public class WordResponse {

    private String wordKey;
    private String content;
    private String description;

    @Builder
    public WordResponse(String wordKey, String content, String description) {
        this.wordKey = wordKey;
        this.content = content;
        this.description = description;
    }

    public static WordResponse of(Word word) {
        return WordResponse.builder()
            .wordKey(word.getWordKey())
            .content(word.getContent())
            .description(word.getDescription())
            .build();
    }
}
