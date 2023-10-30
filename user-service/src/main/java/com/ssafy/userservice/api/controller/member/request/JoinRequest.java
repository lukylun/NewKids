package com.ssafy.userservice.api.controller.member.request;

import com.ssafy.userservice.api.service.member.dto.JoinMemberDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class JoinRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    private Integer age;

    @NotBlank
    private String nickname;

    @Builder
    private JoinRequest(String email, String password, String name, Integer age, String nickname) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.nickname = nickname;
    }

    public JoinMemberDto toJoinMemberDto() {
        return JoinMemberDto.builder()
            .email(this.email)
            .password(this.password)
            .name(this.name)
            .age(this.age)
            .nickname(this.nickname)
            .build();
    }
}
