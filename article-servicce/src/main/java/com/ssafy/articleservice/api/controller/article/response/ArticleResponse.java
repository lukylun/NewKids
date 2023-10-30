package com.ssafy.articleservice.api.controller.article.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleResponse {

    private Long articleId;
    private String title;
    private String subTitle;
    private String writer;
    private String content;
    private LocalDateTime publishedDate;
    private String thumbnailImg;

    @Builder
    public ArticleResponse(Long articleId, String title, String subTitle, String writer, String content, LocalDateTime publishedDate, String thumbnailImg) {
        this.articleId = articleId;
        this.title = title;
        this.subTitle = subTitle;
        this.writer = writer;
        this.content = reContent(content);
        this.publishedDate = publishedDate;
        this.thumbnailImg = thumbnailImg;
    }

    private String reContent(String content) {
        int length = content.length();
        if (length < 100) {
            return content;
        }
        return content.substring(0, 100);
    }
}
