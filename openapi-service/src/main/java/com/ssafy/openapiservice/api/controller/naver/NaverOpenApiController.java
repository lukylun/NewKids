package com.ssafy.openapiservice.api.controller.naver;

import com.ssafy.openapiservice.api.service.naver.NaverOpenApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/openapi-service/api/naver/search")
public class NaverOpenApiController {

    private final NaverOpenApiService naverOpenApiService;

    @GetMapping
    public String searchNaverVocabulary(@RequestParam String query) {

        return naverOpenApiService.searchDictionary(query);
    }
}
