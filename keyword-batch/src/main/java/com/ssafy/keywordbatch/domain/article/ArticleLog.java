package com.ssafy.keywordbatch.domain.article;

import com.ssafy.keywordbatch.domain.TimeBaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleLog extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_log_id")
    private Long id;
    private Long articleId;
    private int count;

    @Builder
    private ArticleLog(Long articleId, int count) {
        this.articleId = articleId;
        this.count = count;
    }
}
