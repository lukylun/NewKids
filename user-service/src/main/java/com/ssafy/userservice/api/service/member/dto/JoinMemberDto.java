package com.ssafy.userservice.api.service.member.dto;

import com.ssafy.userservice.domain.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class JoinMemberDto {

    private String email;
    private String password;
    private String name;
    private Integer age;
    private String nickname;

    @Builder
    private JoinMemberDto(String email, String password, String name, Integer age, String nickname) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.nickname = nickname;
    }

    public Member toEntity(String encryptedPwd, String memberKey) {
        return Member.builder()
            .memberKey(memberKey)
            .email(this.email)
            .encryptedPwd(encryptedPwd)
            .name(this.name)
            .age(this.age)
            .level(1)
            .exp(0)
            .nickname(this.nickname)
            .active(true)
            .build();
    }
}
