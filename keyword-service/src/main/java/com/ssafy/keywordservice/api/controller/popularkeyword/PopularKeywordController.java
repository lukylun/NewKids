package com.ssafy.keywordservice.api.controller.popularkeyword;

import com.ssafy.keywordservice.api.controller.ApiResponse;
import com.ssafy.keywordservice.api.controller.popularkeyword.response.PopularKeywordResponse;
import com.ssafy.keywordservice.api.service.popularkeyword.PopularKeywordQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/keyword-service/api/popular")
public class PopularKeywordController {

    private final PopularKeywordQueryService popularKeywordQueryService;

    /**
     * 인기 키워드 TOP 10 조회 API
     *
     * @return 인기 키워드 TOP 10
     */
    @GetMapping
    public ApiResponse<List<PopularKeywordResponse>> getTopFivePopularKeyword() {
        log.debug("call PopularKeywordController#getTopFivePopularKeyword");

        List<PopularKeywordResponse> responses = popularKeywordQueryService.getTopTenPopularKeyword();
        log.debug("responses={}", responses);

        return ApiResponse.ok(responses);
    }
}
