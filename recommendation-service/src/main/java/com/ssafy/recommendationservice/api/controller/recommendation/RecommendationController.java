package com.ssafy.recommendationservice.api.controller.recommendation;

import com.ssafy.recommendationservice.api.controller.ApiResponse;
import com.ssafy.recommendationservice.api.controller.recommendation.response.AnotherArticleRecommendationResponse;
import com.ssafy.recommendationservice.api.controller.recommendation.response.MainRecommendationResponse;
import com.ssafy.recommendationservice.api.controller.recommendation.response.PeerAgeRecommendationResponse;
import com.ssafy.recommendationservice.api.service.article.ArticleLogQueryService;
import com.ssafy.recommendationservice.api.service.recommendation.RecommendationService;
import com.ssafy.recommendationservice.client.response.ArticleResponse;
import com.ssafy.recommendationservice.client.response.TempResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/recommendation-service/api")
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final ArticleLogQueryService articleLogQueryService;

    @GetMapping("/main")
    public ApiResponse<List<ArticleResponse>> getMainRecommendation() {

        List<ArticleResponse> responses = articleLogQueryService.getHotArticle();

        return ApiResponse.ok(responses);
    }

    @GetMapping("/peer-age")
    public ApiResponse<List<TempResponse>> getPeerAgeRecommendation() {
        List<TempResponse> responses = recommendationService.getPeerAgeRecommendation();
        return ApiResponse.ok(responses);
    }

    @GetMapping("/another-article")
    public ApiResponse<List<AnotherArticleRecommendationResponse>> getAnotherArticleRecommendation(
        @RequestParam Long articleId
    ) {
        List<AnotherArticleRecommendationResponse> responses = recommendationService.getAnotherArticleRecommendation(articleId);
        return ApiResponse.ok(responses);
    }
}
