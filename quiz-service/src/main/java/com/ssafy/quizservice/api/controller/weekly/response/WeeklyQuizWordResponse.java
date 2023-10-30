package com.ssafy.quizservice.api.controller.weekly.response;

import com.ssafy.quizservice.domain.weekly.Problem;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WeeklyQuizWordResponse {

    private int no;
    private String answerWord;
    private String description;
    private List<String> contents = new ArrayList<>();

    @Builder
    private WeeklyQuizWordResponse(int no, String answerWord, String description, List<String> contents) {
        this.no = no;
        this.answerWord = answerWord;
        this.description = description;
        this.contents = contents;
    }

    public static WeeklyQuizWordResponse of(Problem problem) {
        return WeeklyQuizWordResponse.builder()
            .no(problem.getNo())
            .answerWord(problem.getAnswerWord())
            .description(problem.getDescription())
            .contents(problem.getContents())
            .build();
    }

}
