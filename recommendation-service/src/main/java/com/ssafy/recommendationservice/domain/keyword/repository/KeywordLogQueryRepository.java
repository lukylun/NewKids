package com.ssafy.recommendationservice.domain.keyword.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.recommendationservice.domain.keyword.KeywordLog;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static com.ssafy.recommendationservice.domain.keyword.QKeywordLog.keywordLog;

@Repository
public class KeywordLogQueryRepository {

    private final JPAQueryFactory queryFactory;

    public KeywordLogQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Long> findLiveHotKeyword(LocalDateTime targetDateTime) {
        return queryFactory
            .select(keywordLog.keywordId)
            .from(keywordLog)
            .where(keywordLog.createdDate.after(targetDateTime))
            .orderBy(keywordLog.count.desc())
            .limit(10)
            .fetch();
    }

    public List<KeywordLog> findCloudHotKeyword(LocalDateTime targetDateTime) {
        return queryFactory
            .select(keywordLog)
            .from(keywordLog)
            .where(keywordLog.createdDate.after(targetDateTime))
            .orderBy(keywordLog.count.desc())
            .limit(10)
            .fetch();
    }
}
