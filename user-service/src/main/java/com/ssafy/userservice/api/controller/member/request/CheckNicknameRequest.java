package com.ssafy.userservice.api.controller.member.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckNicknameRequest {

    private String nickname;

    @Builder
    private CheckNicknameRequest(String nickname) {
        this.nickname = nickname;
    }
}
