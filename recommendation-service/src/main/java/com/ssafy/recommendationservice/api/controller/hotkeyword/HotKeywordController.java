package com.ssafy.recommendationservice.api.controller.hotkeyword;

import com.ssafy.recommendationservice.api.controller.ApiResponse;
import com.ssafy.recommendationservice.api.controller.hotkeyword.response.LiveResponse;
import com.ssafy.recommendationservice.api.controller.hotkeyword.response.WordCloudResponse;
import com.ssafy.recommendationservice.api.service.hotkeyword.HotKeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/recommendation-service/api/hot-keyword")
public class HotKeywordController {

    private final HotKeywordService hotKeywordService;

    @GetMapping("/live")
    public ApiResponse<List<LiveResponse>> getLiveHotKeyword() {
        LocalDateTime targetDateTime = LocalDateTime.now().minusHours(1);

        List<LiveResponse> responses = hotKeywordService.getLiveHotKeyword(targetDateTime);

        return ApiResponse.ok(responses);
    }

    @GetMapping("/cloud")
    public ApiResponse<List<WordCloudResponse>> getHotKeywordByWordCloud() {
        LocalDateTime targetDateTime = LocalDateTime.now().minusDays(7);

        List<WordCloudResponse> responses = hotKeywordService.getCloudHotKeyword(targetDateTime);

        return ApiResponse.ok(responses);
    }
}
