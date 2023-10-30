package com.ssafy.vocabularyservice.api.service.word.dto;

import com.ssafy.vocabularyservice.domain.word.Word;
import lombok.Builder;
import lombok.Data;

@Data
public class CreateWordDto {

    private String wordKey;
    private String content;
    private String description;

    @Builder
    private CreateWordDto(String wordKey, String content, String description) {
        this.wordKey = wordKey;
        this.content = content;
        this.description = description;
    }

    public Word toEntity() {
        return Word.builder()
            .wordKey(this.wordKey)
            .content(this.content)
            .description(this.description)
            .build();
    }
}
