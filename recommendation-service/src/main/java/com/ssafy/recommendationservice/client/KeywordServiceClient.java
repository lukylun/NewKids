package com.ssafy.recommendationservice.client;

import com.ssafy.recommendationservice.client.request.KeywordRequest;
import com.ssafy.recommendationservice.client.response.KeywordResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "keyword-service")
public interface KeywordServiceClient {

    @PostMapping("/keyword-service/api/client/popular")
    List<KeywordResponse> getKeywords(@RequestBody KeywordRequest request);
}
