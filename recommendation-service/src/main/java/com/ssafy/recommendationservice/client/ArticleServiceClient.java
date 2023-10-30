package com.ssafy.recommendationservice.client;

import com.ssafy.recommendationservice.client.request.ArticleRequest;
import com.ssafy.recommendationservice.client.response.ArticleResponse;
import com.ssafy.recommendationservice.client.response.TempResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "article-service")
public interface ArticleServiceClient {

    @PostMapping("/article-service/api/client/popular")
    List<ArticleResponse> getArticles(@RequestBody ArticleRequest request);

    @PostMapping("/article-service/api/client/temp")
    List<TempResponse> getTemps(@RequestBody ArticleRequest request);
}
