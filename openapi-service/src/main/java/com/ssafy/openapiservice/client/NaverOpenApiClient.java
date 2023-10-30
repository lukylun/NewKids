package com.ssafy.openapiservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naver-api", url = "https://openapi.naver.com/v1/search/encyc.json")
public interface NaverOpenApiClient {

    @GetMapping
    String getVocabulary(
        @RequestHeader("X-Naver-Client-Id") String id,
        @RequestHeader("X-Naver-Client-Secret") String secret,
        @RequestParam String query
    );
}
