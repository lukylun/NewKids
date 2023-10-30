package com.ssafy.analysisservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "article_logs")
public class ArticleLog {

    @Id
    private String id;
    private String memberKey;
    private Long articleId;
    private LocalDateTime createdDate;

    @Builder
    public ArticleLog(String memberKey, Long articleId) {
        this.memberKey = memberKey;
        this.articleId = articleId;
        this.createdDate = LocalDateTime.now();
    }
}
