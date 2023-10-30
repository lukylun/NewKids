package com.ssafy.recommendationservice.api.controller.recommendation.response;

import lombok.Builder;
import lombok.Data;

@Data
public class MainRecommendationResponse {

    private Long articleId;
    private String title;
    private String thumbnailImg;

    @Builder
    public MainRecommendationResponse(Long articleId, String title, String thumbnailImg) {
        this.articleId = articleId;
        this.title = title;
        this.thumbnailImg = thumbnailImg;
    }
}
