package com.ssafy.vocabularyservice.domain.vocabulary.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.vocabularyservice.api.controller.vocabulary.response.VocabularyResponse;
import com.ssafy.vocabularyservice.api.controller.vocabulary.response.WordClientResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static com.ssafy.vocabularyservice.domain.vocabulary.QVocabulary.vocabulary;
import static com.ssafy.vocabularyservice.domain.word.QWord.word;

/**
 * 단어장 Querydsl 저장소
 *
 * @author 임우택
 */
@Repository
public class VocabularyQueryRepository {

    private final JPAQueryFactory queryFactory;

    public VocabularyQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 회원키와 단어키로 단어장 중복 여부 조회
     *
     * @param memberKey 조회할 회원키
     * @param wordKey   조회할 단어키
     * @return 단어장 중복 등록 여부
     */
    public boolean existVocabulary(String memberKey, String wordKey) {
        Integer result = queryFactory
            .selectOne()
            .from(vocabulary)
            .join(vocabulary.word, word)
            .where(
                vocabulary.memberKey.eq(memberKey),
                vocabulary.word.wordKey.eq(wordKey)
            )
            .fetchFirst();
        return result != null;
    }

    public List<VocabularyResponse> findByMemberKey(String memberKey, Boolean check, Pageable pageable) {
        return queryFactory
            .select(Projections.constructor(VocabularyResponse.class,
                vocabulary.id,
                vocabulary.word.wordKey,
                vocabulary.word.content,
                vocabulary.word.description,
                vocabulary.check
            ))
            .from(vocabulary)
            .where(
                vocabulary.memberKey.eq(memberKey),
                isCheck(check)
            )
            .orderBy(vocabulary.createdDate.desc())
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .fetch();
    }

    public long getTotalCountByMemberKey(String memberKey) {
        return queryFactory
            .select(vocabulary.id)
            .from(vocabulary)
            .where(vocabulary.memberKey.eq(memberKey))
            .fetch()
            .size();
    }

    public List<WordClientResponse> findClientByMemberKey(List<Long> options) {
        return queryFactory
            .select(Projections.constructor(WordClientResponse.class,
                vocabulary.word.wordKey,
                vocabulary.word.content,
                vocabulary.word.description
            ))
            .from(vocabulary)
            .join(vocabulary.word, word)
            .where(vocabulary.id.in(options))
            .fetch();
    }

    public List<Long> findIdClientByMemberKey(String memberKey) {
        return queryFactory
            .select(vocabulary.id)
            .from(vocabulary)
            .where(vocabulary.memberKey.eq(memberKey))
            .fetch();
    }

    public long getCheckCountByMemberKey(String memberKey) {
        return queryFactory
            .select(vocabulary.id)
            .from(vocabulary)
            .where(
                vocabulary.memberKey.eq(memberKey),
                vocabulary.check.isTrue()
            )
            .fetch()
            .size();
    }

    private BooleanExpression isCheck(Boolean check) {
        return check ? vocabulary.check.isTrue() : null;
    }
}
