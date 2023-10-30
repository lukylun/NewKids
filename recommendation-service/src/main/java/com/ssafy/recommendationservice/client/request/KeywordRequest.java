package com.ssafy.recommendationservice.client.request;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class KeywordRequest {

    private List<Long> keywordIds = new ArrayList<>();

    @Builder
    public KeywordRequest(List<Long> keywordIds) {
        this.keywordIds = keywordIds;
    }
}
