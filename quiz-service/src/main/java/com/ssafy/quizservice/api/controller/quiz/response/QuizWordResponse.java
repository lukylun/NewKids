package com.ssafy.quizservice.api.controller.quiz.response;

import com.ssafy.quizservice.domain.quiz.Problem;
import lombok.Builder;
import lombok.Data;

@Data
public class QuizWordResponse {

    private int no;
    private String word;
    private String description;

    @Builder
    private QuizWordResponse(int no, String word, String description) {
        this.no = no;
        this.word = word;
        this.description = description;
    }

    public static QuizWordResponse of(Problem problem) {
        return QuizWordResponse.builder()
            .no(problem.getNo())
            .word(problem.getWord())
            .description(problem.getDescription())
            .build();
    }
}
