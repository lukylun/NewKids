package com.ssafy.articleservice.api.service.popular;

import com.ssafy.articleservice.api.controller.article.response.PopularArticleResponse;
import com.ssafy.articleservice.api.controller.article.response.TempResponse;
import com.ssafy.articleservice.domain.article.repository.ArticleQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PopularArticleQueryService {

    private final ArticleQueryRepository articleQueryRepository;

    public List<PopularArticleResponse> getPopularArticle(List<Long> articleIds) {
        return articleQueryRepository.findHitTop5(articleIds);
    }

    public List<TempResponse> getTempArticle(List<Long> articleIds) {
        return articleQueryRepository.findTemp(articleIds);
    }
}
