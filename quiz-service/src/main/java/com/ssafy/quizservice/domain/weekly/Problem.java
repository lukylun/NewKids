package com.ssafy.quizservice.domain.weekly;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Problem {

    private int no;
    private String answerWord;
    private String description;
    private List<String> contents = new ArrayList<>();

    @Builder
    private Problem(int no, String answerWord, String description, List<String> contents) {
        this.no = no;
        this.answerWord = answerWord;
        this.description = description;
        this.contents = contents;
    }
}
