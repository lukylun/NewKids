package com.ssafy.userservice.api.controller.member.response;

import com.ssafy.userservice.domain.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class MemberLevelResponse {

    private int level;
    private int exp;

    @Builder
    public MemberLevelResponse(int level, int exp) {
        this.level = level;
        this.exp = exp;
    }

    public static MemberLevelResponse of(Member member) {
        return MemberLevelResponse.builder()
            .level(member.getLevel())
            .exp(member.getExp())
            .build();
    }
}
