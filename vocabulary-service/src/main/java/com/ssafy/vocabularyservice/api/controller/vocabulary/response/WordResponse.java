package com.ssafy.vocabularyservice.api.controller.vocabulary.response;

import com.ssafy.vocabularyservice.domain.vocabulary.Vocabulary;
import lombok.Builder;
import lombok.Data;

@Data
public class WordResponse {

    private String wordKey;
    private String content;
    private String description;
    private Boolean check;

    @Builder
    public WordResponse(String wordKey, String content, String description, boolean check) {
        this.wordKey = wordKey;
        this.content = content;
        this.description = description;
        this.check = check;
    }

    public static WordResponse of(Vocabulary vocabulary) {
        return WordResponse.builder()
            .wordKey(vocabulary.getWord().getWordKey())
            .content(vocabulary.getWord().getContent())
            .description(vocabulary.getWord().getDescription())
            .check(vocabulary.getCheck())
            .build();
    }
}
