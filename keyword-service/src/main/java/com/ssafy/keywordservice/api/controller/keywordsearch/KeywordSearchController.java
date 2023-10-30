package com.ssafy.keywordservice.api.controller.keywordsearch;

import com.ssafy.keywordservice.api.controller.ApiResponse;
import com.ssafy.keywordservice.api.controller.keywordsearch.response.KeywordSearchResponse;
import com.ssafy.keywordservice.api.service.keywordsearch.KeywordSearchQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/keyword-service/api/my")
public class KeywordSearchController {

    private final KeywordSearchQueryService keywordSearchQueryService;

    @GetMapping("/{memberKey}")
    public ApiResponse<List<KeywordSearchResponse>> getMyKeywordSearch(@PathVariable String memberKey) {
        log.debug("KeywordSearchController#getMyKeywordSearch");
        log.debug("memberKey={}", memberKey);

        List<KeywordSearchResponse> responses = keywordSearchQueryService.getMyKeywordSearch(memberKey);
        log.debug("responses={}", responses);

        return ApiResponse.ok(responses);
    }
}
