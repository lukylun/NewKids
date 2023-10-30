package com.ssafy.userservice.api.controller.member.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditNicknameRequest {

    private String newNickname;

    @Builder
    private EditNicknameRequest(String newNickname) {
        this.newNickname = newNickname;
    }
}
