package com.ssafy.recommendationservice.client;

import com.ssafy.recommendationservice.client.response.FlaskArticleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "external-api", url = "https://j9c205.p.ssafy.io/flask")
public interface FlaskServerClient {

    @GetMapping("/recommend/content-base-filter")
    List<FlaskArticleResponse> getAnotherRecommendation(@RequestParam Long articleId);
}
