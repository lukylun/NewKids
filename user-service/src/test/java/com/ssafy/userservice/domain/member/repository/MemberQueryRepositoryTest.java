package com.ssafy.userservice.domain.member.repository;

import com.ssafy.userservice.IntegrationTestSupport;
import com.ssafy.userservice.api.controller.member.response.MemberResponse;
import com.ssafy.userservice.domain.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 회원 쿼리용 저장소 테스트
 *
 * @author 임우택
 */
class MemberQueryRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private MemberQueryRepository memberQueryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("이미 등록된 이메일이라면 true를 반환한다.")
    @Test
    void existEmailWithTrue() {
        //given
        Member member = createMember();

        //when
        boolean result = memberQueryRepository.existEmail("ssafy@ssafy.com");

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("사용 가능한 이메일이라면 false를 반환한다.")
    @Test
    void existEmailWithFalse() {
        //given

        //when
        boolean result = memberQueryRepository.existEmail("ssafy@ssafy.com");

        //then
        assertThat(result).isFalse();
    }

    @DisplayName("이미 사용중인 닉네임이라면 true를 반환한다.")
    @Test
    void existNicknameWithTrue() {
        //given
        Member member = createMember();

        //when
        boolean result = memberQueryRepository.existNickname("광주C205");

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("사용 가능한 닉네임이라면 false를 반환한다.")
    @Test
    void existNicknameWithFalse() {
        //given

        //when
        boolean result = memberQueryRepository.existNickname("광주C205");

        //then
        assertThat(result).isFalse();
    }

    @DisplayName("회원 고유키로 회원 정보를 조회할 수 있다.")
    @Test
    void findByMemberKey() {
        //given
        Member member = createMember();

        //when
        Optional<MemberResponse> response = memberQueryRepository.findByMemberKey(member.getMemberKey());

        //then
        assertThat(response).isPresent();
        assertThat(response.get())
            .extracting("name", "age", "level", "exp", "nickname")
            .containsExactlyInAnyOrder("김싸피", 10, 1, 0, "광주C205");
    }

    private Member createMember() {
        Member member = Member.builder()
            .memberKey(UUID.randomUUID().toString())
            .email("ssafy@ssafy.com")
            .encryptedPwd("encryptedPwd")
            .name("김싸피")
            .age(10)
            .level(1)
            .exp(0)
            .nickname("광주C205")
            .active(true)
            .build();
        return memberRepository.save(member);
    }
}