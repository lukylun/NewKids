package com.ssafy.articleservice.messagequeue.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ReadArticleDto {

    private String memberKey;
    private Long articleId;

    @Builder
    public ReadArticleDto(String memberKey, Long articleId) {
        this.memberKey = memberKey;
        this.articleId = articleId;
    }
}
