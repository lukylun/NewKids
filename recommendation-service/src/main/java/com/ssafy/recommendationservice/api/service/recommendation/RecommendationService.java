package com.ssafy.recommendationservice.api.service.recommendation;

import com.ssafy.recommendationservice.api.controller.recommendation.response.AnotherArticleRecommendationResponse;
import com.ssafy.recommendationservice.api.controller.recommendation.response.MainRecommendationResponse;
import com.ssafy.recommendationservice.api.controller.recommendation.response.PeerAgeRecommendationResponse;
import com.ssafy.recommendationservice.client.ArticleServiceClient;
import com.ssafy.recommendationservice.client.FlaskServerClient;
import com.ssafy.recommendationservice.client.request.ArticleRequest;
import com.ssafy.recommendationservice.client.response.ArticleResponse;
import com.ssafy.recommendationservice.client.response.FlaskArticleResponse;
import com.ssafy.recommendationservice.client.response.TempResponse;
import com.ssafy.recommendationservice.domain.artilcle.repository.ArticleLogQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecommendationService {

    private final FlaskServerClient flaskServerClient;

    // TODO: 2023-10-05 수정 필요
    private final ArticleLogQueryRepository articleLogQueryRepository;
    private final ArticleServiceClient articleServiceClient;

    public List<TempResponse> getPeerAgeRecommendation() {
        List<Long> articleIds = articleLogQueryRepository.tempArticle();

        ArticleRequest request = ArticleRequest.builder()
            .articleIds(articleIds)
            .build();

        List<TempResponse> responses = articleServiceClient.getTemps(request);

        return responses;
    }

    public List<AnotherArticleRecommendationResponse> getAnotherArticleRecommendation(Long articleId) {

        List<FlaskArticleResponse> getResponses = flaskServerClient.getAnotherRecommendation(articleId);

        return getResponses.stream().map(response -> AnotherArticleRecommendationResponse.builder()
            .articleId(response.getArticle_id())
            .title(response.getTitle())
            .thumbnailImg(response.getThumbnail_img())
            .build())
            .collect(Collectors.toList());
    }
}
