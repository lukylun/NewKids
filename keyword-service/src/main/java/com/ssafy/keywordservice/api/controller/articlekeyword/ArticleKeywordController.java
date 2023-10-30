package com.ssafy.keywordservice.api.controller.articlekeyword;

import com.ssafy.keywordservice.api.controller.ApiResponse;
import com.ssafy.keywordservice.api.controller.articlekeyword.response.ArticleKeywordResponse;
import com.ssafy.keywordservice.api.controller.keyword.request.CreatedKeywordRequest;
import com.ssafy.keywordservice.api.service.articlekeyword.ArticleKeywordQueryService;
import com.ssafy.keywordservice.api.service.articlekeyword.ArticleKeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/keyword-service/api/{articleKey}/articles")
public class ArticleKeywordController {

    private final ArticleKeywordService articleKeywordService;
    private final ArticleKeywordQueryService articleKeywordQueryService;

    /**
     * 뉴스 기사 키워드 등록 API
     *
     * @param request 등록할 키워드 정보
     * @param articleKey 뉴스 기사 고유키
     * @return 등록된 뉴스 기사 키워드 정보
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ArticleKeywordResponse> createArticleKeyword(
        @Valid @RequestBody CreatedKeywordRequest request,
        @PathVariable Long articleKey
    ) {
        log.debug("ArticleKeywordController#createArticleKeyword");
        log.debug("request={}", request);

        ArticleKeywordResponse response = articleKeywordService.createArticleKeyword(articleKey, request.getWord());
        log.debug("response={}", response);

        return ApiResponse.created(response);
    }

    /**
     * 뉴스 기사 키워드 조회 API
     *
     * @param articleKey 조회할 뉴스 기사 PK
     * @return 조회된 키워드 목록
     */
    @GetMapping
    public ApiResponse<List<ArticleKeywordResponse>> searchArticleKeyword(@PathVariable Long articleKey) {
        log.debug("ArticleKeywordController#searchArticleKeyword");
        log.debug("articleKey={}", articleKey);

        List<ArticleKeywordResponse> responses = articleKeywordQueryService.getArticleKeywords(articleKey);
        log.debug("responses={}", responses);

        return ApiResponse.ok(responses);
    }
}
