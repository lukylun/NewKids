package com.ssafy.keywordservice.domain.articlekeyword.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.keywordservice.api.controller.articlekeyword.response.ArticleKeywordResponse;
import com.ssafy.keywordservice.domain.keyword.Keyword;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static com.ssafy.keywordservice.domain.articlekeyword.QArticleKeyword.articleKeyword;
import static com.ssafy.keywordservice.domain.keyword.QKeyword.keyword;

@Repository
public class ArticleKeywordQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ArticleKeywordQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<ArticleKeywordResponse> findByArticleKey(Long articleKey) {
        return queryFactory
            .select(Projections.constructor(ArticleKeywordResponse.class,
                articleKeyword.keyword.id,
                articleKeyword.keyword.word
            ))
            .from(articleKeyword)
            .join(articleKeyword.keyword, keyword)
            .where(articleKeyword.articleKey.eq(articleKey))
            .fetch();
    }

    public List<Keyword> findKeywordIdsByArticleKey(Long articleKey) {
        return queryFactory
            .select(articleKeyword.keyword)
            .from(articleKeyword)
            .join(articleKeyword.keyword, keyword)
            .where(articleKeyword.articleKey.eq(articleKey))
            .fetch();
    }

    public List<Long> findKeywordIdByArticleKey(Long articleKey) {
        return queryFactory
            .select(articleKeyword.keyword.id)
            .from(articleKeyword)
            .where(articleKeyword.articleKey.eq(articleKey))
            .fetch();
    }
}
