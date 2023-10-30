package com.ssafy.userservice.domain.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.userservice.api.controller.member.response.MemberResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.Optional;

import static com.ssafy.userservice.domain.member.QMember.member;

/**
 * 회원 쿼리용 저장소
 *
 * @author 임우택
 */
@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MemberQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 이메일 중복 체크
     *
     * @param email 중복 체크할 대상 이메일
     * @return 존재하면 true, 존재하지 않으면 false
     */
    public boolean existEmail(String email) {
        Integer result = queryFactory
            .selectOne()
            .from(member)
            .where(member.email.eq(email))
            .fetchFirst();

        return result != null;
    }

    /**
     * 닉네임 중복 체크
     *
     * @param nickname 중복 체크할 대상 닉네임
     * @return 존재하면 true, 존재하지 않으면 false
     */
    public boolean existNickname(String nickname) {
        Integer result = queryFactory
            .selectOne()
            .from(member)
            .where(member.nickname.eq(nickname))
            .fetchFirst();

        return result != null;
    }

    /**
     * 회원 정보 조회
     *
     * @param memberKey 조회할 대상의 이메일
     * @return 조회된 회원의 정보
     */
    public Optional<MemberResponse> findByMemberKey(String memberKey) {
        MemberResponse response = queryFactory
            .select(Projections.constructor(MemberResponse.class,
                member.email,
                member.name,
                member.age,
                member.level,
                member.exp,
                member.nickname
            ))
            .from(member)
            .where(member.memberKey.eq(memberKey))
            .fetchFirst();
        return Optional.ofNullable(response);
    }
}
