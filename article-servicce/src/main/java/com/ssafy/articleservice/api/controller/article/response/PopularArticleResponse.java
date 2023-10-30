package com.ssafy.articleservice.api.controller.article.response;

import lombok.Builder;
import lombok.Data;

@Data
public class PopularArticleResponse {

    private Long articleId;
    private String title;
    private String thumbnailImg;

    @Builder
    public PopularArticleResponse(Long articleId, String title, String thumbnailImg) {
        this.articleId = articleId;
        this.title = title;
        this.thumbnailImg = thumbnailImg;
    }
}
