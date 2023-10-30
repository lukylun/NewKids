package com.ssafy.recommendationservice.client.response;

import lombok.Builder;
import lombok.Data;

@Data
public class ArticleResponse {

    private Long articleId;
    private String thumbnailImg;
    private String title;

    @Builder
    private ArticleResponse(Long articleId, String thumbnailImg, String title) {
        this.articleId = articleId;
        this.thumbnailImg = thumbnailImg;
        this.title = title;
    }
}
