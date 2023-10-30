package com.ssafy.quizservice.messagequeue.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberExpDto {

    private String memberKey;
    private int exp;

    @Builder
    public MemberExpDto(String memberKey, int exp) {
        this.memberKey = memberKey;
        this.exp = exp;
    }
}
