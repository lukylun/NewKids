package com.ssafy.keywordservice.api.controller.client;

import com.ssafy.keywordservice.api.controller.client.request.KeywordRequest;
import com.ssafy.keywordservice.api.controller.client.response.KeywordResponse;
import com.ssafy.keywordservice.api.service.articlekeyword.ArticleKeywordQueryService;
import com.ssafy.keywordservice.api.service.keyword.KeywordQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/keyword-service/api/client")
public class KeywordServiceClientController {

    private final ArticleKeywordQueryService articleKeywordQueryService;
    private final KeywordQueryService keywordQueryService;

    @GetMapping("/{articleId}")
    public List<Long> getKeywordIds(@PathVariable Long articleId) {
        log.debug("call KeywordServiceClientController#getKeywordIds");
        log.debug("articleId={}", articleId);

        List<Long> keywordIds = articleKeywordQueryService.getArticleKeywordIds(articleId);
        log.debug("keywordIds={}", keywordIds);

        return keywordIds;
    }

    @PostMapping("/popular")
    public List<KeywordResponse> getKeywords(@RequestBody KeywordRequest request) {

        List<KeywordResponse> responses = keywordQueryService.getKeywordByIds(request.getKeywordIds());

        return responses;
    }
}
