package com.ssafy.quizservice.client.response;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class KeywordQuizClientResponse {

    private String answer;
    private String description;
    private List<String> contents = new ArrayList<>();

    @Builder
    public KeywordQuizClientResponse(String answer, String description, List<String> contents) {
        this.answer = answer;
        this.description = description;
        this.contents = contents;
    }
}
