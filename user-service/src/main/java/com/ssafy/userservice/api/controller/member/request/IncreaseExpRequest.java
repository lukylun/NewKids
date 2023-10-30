package com.ssafy.userservice.api.controller.member.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IncreaseExpRequest {

    private Integer exp;

    @Builder
    private IncreaseExpRequest(Integer exp) {
        this.exp = exp;
    }
}
