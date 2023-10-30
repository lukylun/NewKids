package com.ssafy.articleservice.api.controller.article.response;

import com.ssafy.articleservice.domain.article.Article;
import com.ssafy.articleservice.domain.articleread.ArticleRead;
import lombok.Builder;
import lombok.Data;

@Data
public class ArticleReadResponse {

    private Long articleId;
    private String title;
    private String thumbnailImg;

    @Builder
    public ArticleReadResponse(Long articleId, String title, String thumbnailImg) {
        this.articleId = articleId;
        this.title = title;
        this.thumbnailImg = thumbnailImg;
    }

    public static ArticleReadResponse of(Article article) {
        return ArticleReadResponse.builder()
            .articleId(article.getId())
            .title(article.getTitle())
            .thumbnailImg(article.getThumbnailImg())
            .build();
    }

}
