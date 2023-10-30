package com.ssafy.articleservice.api.controller.article.response;

import com.ssafy.articleservice.domain.article.Article;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDetailResponse {

    private Long articleId;
    private String title;
    private String subTitle;
    private String writer;
    private LocalDateTime publishedDate;
    private String content;
    private String thumbnailImg;

    @Builder
    private ArticleDetailResponse(Long articleId, String title, String subTitle, String writer, LocalDateTime publishedDate, String content, String thumbnailImg) {
        this.articleId = articleId;
        this.title = title;
        this.subTitle = subTitle;
        this.writer = writer;
        this.publishedDate = publishedDate;
        this.content = content;
        this.thumbnailImg = thumbnailImg;
    }

    public static ArticleDetailResponse of(Article article) {
        return ArticleDetailResponse.builder()
            .articleId(article.getId())
            .title(article.getTitle())
            .subTitle(article.getSubTitle())
            .writer(article.getWriter())
            .publishedDate(article.getPublishedDate())
            .content(article.getHtmlContent())
            .thumbnailImg(article.getThumbnailImg())
            .build();
    }
}
