package com.ssafy.recommendationservice.client.request;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleRequest {

    private List<Long> articleIds = new ArrayList<>();

    @Builder
    public ArticleRequest(List<Long> articleIds) {
        this.articleIds = articleIds;
    }
}
