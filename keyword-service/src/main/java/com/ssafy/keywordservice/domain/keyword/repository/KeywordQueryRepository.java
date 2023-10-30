package com.ssafy.keywordservice.domain.keyword.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.keywordservice.api.controller.client.response.KeywordResponse;
import com.ssafy.keywordservice.domain.keyword.Keyword;
import com.ssafy.keywordservice.domain.keyword.QKeyword;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static com.ssafy.keywordservice.domain.keyword.QKeyword.keyword;
import static com.ssafy.keywordservice.domain.keywordsearch.QKeywordSearch.keywordSearch;

@Repository
public class KeywordQueryRepository {

    private final JPAQueryFactory queryFactory;

    public KeywordQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Keyword> findTop10Keyword() {
        return queryFactory
            .select(keywordSearch.keyword)
            .from(keywordSearch)
            .join(keywordSearch.keyword, keyword)
            .groupBy(keywordSearch.keyword.word)
            .orderBy(keywordSearch.count.sum().desc())
            .limit(10)
            .fetch();
    }

    public List<Keyword> findByIdNotIn(List<Long> keywordIds) {
        return queryFactory
            .select(keyword)
            .from(keyword)
            .where(keyword.id.notIn(keywordIds))
            .orderBy(keyword.createdDate.desc())
            .limit(30)
            .fetch();
    }

    public List<KeywordResponse> findKeywordByIdIn(List<Long> keywordIds) {
        return queryFactory
            .select(Projections.constructor(KeywordResponse.class,
                keyword.id,
                keyword.word
            ))
            .from(keyword)
            .where(keyword.id.in(keywordIds))
            .fetch();
    }
}
