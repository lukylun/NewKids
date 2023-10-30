package com.ssafy.userservice.api.controller.member.response;

import com.ssafy.userservice.domain.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class JoinMemberResponse {

    private String email;
    private String name;
    private int age;
    private String nickname;

    @Builder
    public JoinMemberResponse(String email, String name, int age, String nickname) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.nickname = nickname;
    }

    public static JoinMemberResponse of(Member member) {
        return JoinMemberResponse.builder()
            .email(member.getEmail())
            .name(member.getName())
            .age(member.getAge())
            .nickname(member.getNickname())
            .build();
    }
}
