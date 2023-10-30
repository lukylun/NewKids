package com.ssafy.articleservice.api.controller.article.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateArticleReadRequest {

    @NotNull
    private Long articleId;

    @Builder
    private CreateArticleReadRequest(Long articleId) {
        this.articleId = articleId;
    }
}
