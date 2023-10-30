package com.ssafy.recommendationservice.domain.artilcle.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.recommendationservice.domain.artilcle.QArticleLog.articleLog;

@Repository
public class ArticleLogQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ArticleLogQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Long> findHotArticle() {
        return queryFactory
            .select(articleLog.articleId)
            .from(articleLog)
            .orderBy(
                articleLog.createdDate.desc(),
                articleLog.count.desc(),
                articleLog.id.asc()
            )
            .limit(5)
            .fetch();
    }

    public List<Long> tempArticle() {
        return queryFactory
            .select(articleLog.articleId)
            .from(articleLog)
            .orderBy(
                articleLog.count.desc(),
                articleLog.id.asc()
            )
            .limit(6)
            .fetch();
    }
}
