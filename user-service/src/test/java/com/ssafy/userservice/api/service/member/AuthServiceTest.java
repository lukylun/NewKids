package com.ssafy.userservice.api.service.member;

import com.ssafy.userservice.IntegrationTestSupport;
import com.ssafy.userservice.domain.member.Member;
import com.ssafy.userservice.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AuthServiceTest extends IntegrationTestSupport {

    @Autowired
    private AuthService authService;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("이미 등록된 이메일이라면 true를 반환한다.")
    @Test
    void checkEmailWithTrue() {
        //given
        Member member = createMember();

        //when
        boolean result = authService.checkEmail("ssafy@ssafy.com");

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("사용 가능한 이메일이라면 false를 반환한다.")
    @Test
    void checkEmailWithFalse() {
        //given

        //when
        boolean result = authService.checkEmail("ssafy@ssafy.com");

        //then
        assertThat(result).isFalse();
    }

    @DisplayName("이미 등록된 닉네임이라면 true를 반환한다.")
    @Test
    void checkNicknameWithTrue() {
        //given
        Member member = createMember();

        //when
        boolean result = authService.checkNickname("광주C205");

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("사용 가능한 닉네임이라면 false를 반환한다.")
    @Test
    void checkNicknameWithFalse() {
        //given

        //when
        boolean result = authService.checkNickname("광주C205");

        //then
        assertThat(result).isFalse();
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