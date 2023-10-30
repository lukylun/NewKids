package com.ssafy.recommendationservice.api.service.article;

import com.ssafy.recommendationservice.client.ArticleServiceClient;
import com.ssafy.recommendationservice.client.request.ArticleRequest;
import com.ssafy.recommendationservice.client.response.ArticleResponse;
import com.ssafy.recommendationservice.domain.artilcle.repository.ArticleLogQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ArticleLogQueryService {

    private final ArticleLogQueryRepository articleLogQueryRepository;
    private final ArticleServiceClient articleServiceClient;

    public List<ArticleResponse> getHotArticle() {
        List<Long> hotArticleIds = articleLogQueryRepository.findHotArticle();

        ArticleRequest request = ArticleRequest.builder()
            .articleIds(hotArticleIds)
            .build();

        List<ArticleResponse> articleResponses = articleServiceClient.getArticles(request);

        return articleResponses;
    }
}
