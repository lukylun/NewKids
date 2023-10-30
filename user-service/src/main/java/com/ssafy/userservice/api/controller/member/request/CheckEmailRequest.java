package com.ssafy.userservice.api.controller.member.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CheckEmailRequest {

    @NotEmpty
    private String email;

    @Builder
    private CheckEmailRequest(String email) {
        this.email = email;
    }
}
