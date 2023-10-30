package com.ssafy.keywordservice.api.controller.articlekeyword.response;

import com.ssafy.keywordservice.domain.articlekeyword.ArticleKeyword;
import lombok.Builder;
import lombok.Data;

@Data
public class ArticleKeywordResponse {

    private Long keywordId;
    private String word;

    @Builder
    public ArticleKeywordResponse(Long keywordId, String word) {
        this.keywordId = keywordId;
        this.word = word;
    }

    public static ArticleKeywordResponse of(ArticleKeyword articleKeyword) {
        return ArticleKeywordResponse.builder()
            .keywordId(articleKeyword.getId())
            .word(articleKeyword.getKeyword().getWord())
            .build();
    }
}
