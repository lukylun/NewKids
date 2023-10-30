package com.ssafy.quizservice.api.controller.weekly.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckAnswerRequest {

    private String answer;

    @Builder
    private CheckAnswerRequest(String answer) {
        this.answer = answer;
    }
}
