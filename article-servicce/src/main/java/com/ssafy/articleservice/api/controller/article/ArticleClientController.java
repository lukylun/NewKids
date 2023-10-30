package com.ssafy.articleservice.api.controller.article;

import com.ssafy.articleservice.api.controller.article.request.ArticleRequest;
import com.ssafy.articleservice.api.controller.article.response.PopularArticleResponse;
import com.ssafy.articleservice.api.controller.article.response.TempResponse;
import com.ssafy.articleservice.api.service.popular.PopularArticleQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/article-service/api/client")
public class ArticleClientController {

    private final PopularArticleQueryService popularArticleQueryService;

    @PostMapping("/popular")
    public List<PopularArticleResponse> popularArticle(@RequestBody ArticleRequest request) {
        log.debug("call ArticleClientController#popularArticle");

        List<PopularArticleResponse> responses = popularArticleQueryService.getPopularArticle(request.getArticleIds());
        log.debug("responses={}", responses);

        return responses;
    }

    @PostMapping("/temp")
    public List<TempResponse> getTempUrl(@RequestBody ArticleRequest request) {
        List<TempResponse> responses = popularArticleQueryService.getTempArticle(request.getArticleIds());
        return responses;
    }
}
