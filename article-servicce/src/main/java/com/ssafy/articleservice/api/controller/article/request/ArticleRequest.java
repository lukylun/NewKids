package com.ssafy.articleservice.api.controller.article.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ArticleRequest {

    private List<Long> articleIds = new ArrayList<>();

    @Builder
    public ArticleRequest(List<Long> articleIds) {
        this.articleIds = articleIds;
    }
}
