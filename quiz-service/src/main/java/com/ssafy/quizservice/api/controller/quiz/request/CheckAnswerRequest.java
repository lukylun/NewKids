package com.ssafy.quizservice.api.controller.quiz.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CheckAnswerRequest {

    @NotBlank(message = "정답 입력은 필수입니다.")
    private String answer;

    @Builder
    private CheckAnswerRequest(String answer) {
        this.answer = answer;
    }
}
