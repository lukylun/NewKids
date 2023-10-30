package com.ssafy.analysisservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "keyword-service")
public interface KeywordServiceClient {

    @GetMapping("/keyword-service/api/client/{articleId}")
    List<Long> getKeywordIds(@PathVariable Long articleId);
}
