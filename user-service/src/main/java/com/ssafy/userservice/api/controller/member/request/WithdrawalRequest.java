package com.ssafy.userservice.api.controller.member.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WithdrawalRequest {

    private String password;

    @Builder
    private WithdrawalRequest(String password) {
        this.password = password;
    }
}
