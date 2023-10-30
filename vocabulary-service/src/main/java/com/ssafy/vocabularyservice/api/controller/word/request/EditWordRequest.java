package com.ssafy.vocabularyservice.api.controller.word.request;

import com.ssafy.vocabularyservice.api.service.word.dto.EditWordDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class EditWordRequest {

    @NotBlank(message = "단어는 필수입니다.")
    private String content;

    @NotBlank(message = "설명은 필수입니다.")
    private String description;

    @Builder
    private EditWordRequest(String content, String description) {
        this.content = content;
        this.description = description;
    }

    public EditWordDto toEditWordDto() {
        return EditWordDto.builder()
            .content(this.content)
            .description(this.description)
            .build();
    }
}
