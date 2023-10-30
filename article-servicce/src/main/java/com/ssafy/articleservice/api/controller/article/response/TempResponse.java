package com.ssafy.articleservice.api.controller.article.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TempResponse {

    private Long articleId;
    private String title;
    private String thumbnailImg;
    private String writer;
    private LocalDateTime publishedDate;

    public TempResponse(Long articleId, String title, String thumbnailImg, String writer, LocalDateTime publishedDate) {
        this.articleId = articleId;
        this.title = title;
        this.thumbnailImg = thumbnailImg;
        this.writer = writer;
        this.publishedDate = publishedDate;
    }
}
