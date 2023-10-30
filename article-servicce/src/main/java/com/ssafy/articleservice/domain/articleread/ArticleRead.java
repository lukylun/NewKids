package com.ssafy.articleservice.domain.articleread;

import com.ssafy.articleservice.domain.TimeBaseEntity;
import com.ssafy.articleservice.domain.article.Article;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleRead extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_read_id")
    private Long id;

    private String memberKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Builder
    private ArticleRead(String memberKey, Article article) {
        this.memberKey = memberKey;
        this.article = article;
    }
}
