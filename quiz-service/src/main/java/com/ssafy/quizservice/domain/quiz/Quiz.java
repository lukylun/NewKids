package com.ssafy.quizservice.domain.quiz;

import com.ssafy.quizservice.domain.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@Getter
@RedisHash(value = "quiz")
public class Quiz extends TimeBaseEntity {

    @Id
    private String memberKey;
    private int rightAnswer;
    private List<Problem> problems = new ArrayList<>();

    @Builder
    private Quiz(String memberKey, int rightAnswer, List<Problem> problems) {
        this.memberKey = memberKey;
        this.rightAnswer = rightAnswer;
        this.problems = problems;
    }

    public void removeProblem(boolean isCorrect) {
        if (isCorrect) {
            rightAnswer++;
        }
        problems.remove(0);
    }
}
