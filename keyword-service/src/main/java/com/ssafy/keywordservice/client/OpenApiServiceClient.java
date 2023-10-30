package com.ssafy.keywordservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openapi-service")
public interface OpenApiServiceClient {

    @GetMapping("/openapi-service/api/naver/search")
    String searchNaverVocabulary(@RequestParam String query);
}
