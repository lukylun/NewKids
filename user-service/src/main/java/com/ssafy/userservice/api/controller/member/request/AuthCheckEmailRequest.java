package com.ssafy.userservice.api.controller.member.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthCheckEmailRequest {

    private String email;
    private String authNumber;

    @Builder
    private AuthCheckEmailRequest(String email, String authNumber) {
        this.email = email;
        this.authNumber = authNumber;
    }
}
