package com.ssafy.quizservice.domain.quiz;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Problem {

    private int no;
    private String word;
    private String description;

    @Builder
    private Problem(int no, String word, String description) {
        this.no = no;
        this.word = word;
        this.description = description;
    }
}
