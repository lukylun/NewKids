package com.ssafy.vocabularyservice.api.controller.word.request;

import com.ssafy.vocabularyservice.api.service.word.dto.CreateWordDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CreateWordRequest {

    @NotEmpty(message = "단어 키는 필수입니다.")
    private String wordKey;

    @NotEmpty(message = "단어는 필수입니다.")
    private String content;

    @NotEmpty(message = "설명은 필수입니다.")
    private String description;

    @Builder
    private CreateWordRequest(String wordKey, String content, String description) {
        this.wordKey = wordKey;
        this.content = content;
        this.description = description;
    }

    public CreateWordDto toCreateWordDto() {
        return CreateWordDto.builder()
            .wordKey(this.wordKey)
            .content(this.content)
            .description(this.description)
            .build();
    }
}
