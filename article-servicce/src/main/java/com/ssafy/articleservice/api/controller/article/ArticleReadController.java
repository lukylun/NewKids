package com.ssafy.articleservice.api.controller.article;

import com.ssafy.articleservice.api.controller.ApiResponse;
import com.ssafy.articleservice.api.controller.article.request.CreateArticleReadRequest;
import com.ssafy.articleservice.api.controller.article.response.ArticleReadResponse;
import com.ssafy.articleservice.api.service.articleread.ArticleReadQueryService;
import com.ssafy.articleservice.api.service.articleread.ArticleReadService;
import com.ssafy.articleservice.messagequeue.KafkaProducer;
import com.ssafy.articleservice.messagequeue.dto.ReadArticleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/article-service/api/read")
public class ArticleReadController {

    private final ArticleReadService articleReadService;
    private final ArticleReadQueryService articleReadQueryService;
    private final KafkaProducer kafkaProducer;

    /**
     * 읽은 뉴스 기사 목록 등록 API
     *
     * @param memberKey 회원 고유키
     * @param request   등록할 뉴스 기사의 PK
     * @return 등록된 뉴스 기사 정보
     */
    @PostMapping("/{memberKey}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ArticleReadResponse> createArticleRead(@PathVariable String memberKey, @Valid @RequestBody CreateArticleReadRequest request) {
        log.debug("call ArticleReadController#createArticleRead");
        log.debug("memberKey={}", memberKey);
        log.debug("articleId={}", request.getArticleId());

        ArticleReadResponse response = articleReadService.createArticleRead(memberKey, request.getArticleId());
        log.debug("response={}", response);

        ReadArticleDto readArticleDto = ReadArticleDto.builder()
            .memberKey(memberKey)
            .articleId(request.getArticleId())
            .build();

        //kafka 통신 -> analysis-service
        kafkaProducer.send("read-article-topic", readArticleDto);

        return ApiResponse.created(response);
    }

    /**
     * 읽은 뉴스 기사 목록 조회 API
     * @param memberKey 조회할 회원 고유키
     * @param pageNum 페이지 번호
     * @return 조회된 읽은 뉴스 기사 목록
     */
    @GetMapping("/{memberKey}")
    public ApiResponse<Page<ArticleReadResponse>> getArticleRead(
        @PathVariable String memberKey,
        @RequestParam(defaultValue = "1") Integer pageNum
    ) {
        log.debug("call ArticleReadController#getArticleRead");
        log.debug("memberKey={}", memberKey);

        PageRequest pageRequest = PageRequest.of(pageNum - 1, 8);
        Page<ArticleReadResponse> response = articleReadQueryService.getMyArticleRead(memberKey, pageRequest);
        log.debug("response={}", response.getContent());

        return ApiResponse.ok(response);
    }
}
