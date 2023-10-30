package com.ssafy.articleservice.domain.article.repository.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleSearchCond {

    private String content;
    private LocalDateTime startedDate;
    private LocalDateTime endedDate;

    @Builder
    public ArticleSearchCond(String content, LocalDateTime startedDate, LocalDateTime endedDate) {
        this.content = content;
        this.startedDate = startedDate;
        this.endedDate = endedDate;
    }
}
