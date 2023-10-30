package com.ssafy.articleservice.domain.articleread.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.articleservice.api.controller.article.response.ArticleReadResponse;
import com.ssafy.articleservice.domain.article.QArticle;
import com.ssafy.articleservice.domain.articleread.QArticleRead;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.articleservice.domain.article.QArticle.article;
import static com.ssafy.articleservice.domain.articleread.QArticleRead.articleRead;

@Repository
public class ArticleReadQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ArticleReadQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<ArticleReadResponse> findByMemberKey(String memberKey, Pageable pageable) {
        List<Long> ids = queryFactory
            .select(articleRead.id)
            .from(articleRead)
            .where(articleRead.memberKey.eq(memberKey))
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(articleRead.createdDate.desc())
            .fetch();

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory
            .select(Projections.constructor(ArticleReadResponse.class,
                articleRead.article.id,
                articleRead.article.title,
                articleRead.article.thumbnailImg
            ))
            .from(articleRead)
            .join(articleRead.article, article)
            .where(articleRead.id.in(ids))
            .orderBy(articleRead.createdDate.desc())
            .fetch();
    }

    public long getTotalCount(String memberKey) {
        return queryFactory
            .select(articleRead.id)
            .from(articleRead)
            .where(articleRead.memberKey.eq(memberKey))
            .fetch()
            .size();
    }
}
