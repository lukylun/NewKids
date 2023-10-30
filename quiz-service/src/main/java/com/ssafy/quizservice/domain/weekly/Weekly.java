package com.ssafy.quizservice.domain.weekly;

import com.ssafy.quizservice.domain.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@Getter
@RedisHash(value = "weekly")
public class Weekly extends TimeBaseEntity {

    @Id
    private String memberKey;
    private int rightAnswer;
    private List<Problem> problems = new ArrayList<>();

    @Builder
    private Weekly(String memberKey, int rightAnswer, List<Problem> problems) {
        this.memberKey = memberKey;
        this.rightAnswer = rightAnswer;
        this.problems = problems;
    }

    public void removeProblem(boolean isAnswer) {
        if (isAnswer) {
            rightAnswer++;
        }
        problems.remove(0);
    }
}
