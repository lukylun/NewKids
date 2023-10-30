package com.ssafy.articleservice.api.controller.article;

import com.ssafy.articleservice.api.controller.ApiResponse;
import com.ssafy.articleservice.api.controller.article.response.ArticleDetailResponse;
import com.ssafy.articleservice.api.controller.article.response.ArticleResponse;
import com.ssafy.articleservice.api.service.article.ArticleQueryService;
import com.ssafy.articleservice.api.service.article.ArticleService;
import com.ssafy.articleservice.domain.article.repository.dto.ArticleSearchCond;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 뉴스 기사 API
 *
 * @author 임우택
 */
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/article-service/api")
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleQueryService articleQueryService;

    /**
     * 뉴스 기사 조회 API
     *
     * @param content 검색할 제목 또는 내용
     * @param pageNum 페이지 번호
     * @param startDate 검색 범위 시작일: yyyy-MM-dd
     * @param endDate 검색 범위 종료일: yyyy-MM-dd
     * @return 뉴스 기사 검색 결과
     */
    @GetMapping
    public ApiResponse<Page<ArticleResponse>> getArticles(
        @RequestParam(required = false) String content,
        @RequestParam(defaultValue = "1") Integer pageNum,
        @RequestParam String startDate,
        @RequestParam String endDate
    ) {
        LocalDateTime startedDate = createLocalDateTime(startDate);
        LocalDateTime endedDate = createLocalDateTime(endDate).plusDays(1);

        PageRequest pageRequest = PageRequest.of(pageNum - 1, 10);
        ArticleSearchCond cond = ArticleSearchCond.builder()
            .content(content)
            .startedDate(startedDate)
            .endedDate(endedDate)
            .build();

        Page<ArticleResponse> response = articleQueryService.getArticles(cond, pageRequest);

        return ApiResponse.ok(response);
    }

    /**
     * 뉴스 기사 상세 조회 API
     * @param articleId 조회할 기사의 PK
     * @return 조회된 뉴스 기사의 정보
     */
    @GetMapping("/{articleId}")
    public ApiResponse<ArticleDetailResponse> getArticle(@PathVariable Long articleId) {
        log.debug("ArticleController#getArticle");
        log.debug("request={}", articleId);

        ArticleDetailResponse response = articleQueryService.getArticle(articleId);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }

    /**
     * 뉴스 기사 삭제 API
     * @param articleId 삭제할 뉴스 기사 PK
     * @return 삭제 결과
     */
    @DeleteMapping("/{articleId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<ArticleResponse> removeArticle(@PathVariable Long articleId) {
        articleService.removeArticle(articleId);
        return ApiResponse.found(null);
    }

    private LocalDateTime createLocalDateTime(String date) {
        String[] splitDate = date.split("-");
        return LocalDateTime.of(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[2]), 0, 0);
    }
}
