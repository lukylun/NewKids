package com.ssafy.userservice.api.controller.member.response;

import com.ssafy.userservice.domain.member.Member;
import lombok.Builder;
import lombok.Data;

/**
 * 회원 정보 응답 객체
 *
 * @author 임우택
 */
@Data
public class MemberResponse {

    private String email;
    private String name;
    private int age;
    private int level;
    private int exp;
    private String nickname;

    @Builder
    public MemberResponse(String email, String name, int age, int level, int exp, String nickname) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.level = level;
        this.exp = exp;
        this.nickname = nickname;
    }

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
            .name(member.getName())
            .age(member.getAge())
            .level(member.getLevel())
            .exp(member.getExp())
            .nickname(member.getNickname())
            .build();
    }
}
