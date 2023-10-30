package com.ssafy.quizservice.client;

import com.ssafy.quizservice.client.response.KeywordQuizClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "keyword-service")
public interface KeywordServiceClient {

    @GetMapping("/keyword-service/api/weekly-quiz")
    List<KeywordQuizClientResponse> getWeeklyQuizKeywords();
}
