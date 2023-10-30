package com.ssafy.vocabularyservice.api.service.word.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class EditWordDto {

    private String content;
    private String description;

    @Builder
    private EditWordDto(String content, String description) {
        this.content = content;
        this.description = description;
    }
}
