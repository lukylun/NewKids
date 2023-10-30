package com.ssafy.quizservice.api.controller.weekly.response;

import lombok.Builder;
import lombok.Data;

@Data
public class WeeklyQuizResultResponse {

    private int totalScore;
    private int rightQuizCount;

    @Builder
    public WeeklyQuizResultResponse(int totalScore, int rightQuizCount) {
        this.totalScore = totalScore;
        this.rightQuizCount = rightQuizCount;
    }

    public static WeeklyQuizResultResponse of(int count) {
        return WeeklyQuizResultResponse.builder()
            .totalScore(count * 10)
            .rightQuizCount(count)
            .build();
    }
}
