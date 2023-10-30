package com.ssafy.keywordservice.api.controller.client.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class KeywordRequest {

    private List<Long> keywordIds = new ArrayList<>();

    @Builder
    public KeywordRequest(List<Long> keywordIds) {
        this.keywordIds = keywordIds;
    }
}
