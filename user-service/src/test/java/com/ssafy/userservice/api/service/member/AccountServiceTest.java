package com.ssafy.userservice.api.service.member;

import com.ssafy.userservice.IntegrationTestSupport;
import com.ssafy.userservice.api.controller.member.response.MemberResponse;
import com.ssafy.userservice.domain.member.Member;
import com.ssafy.userservice.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AccountServiceTest extends IntegrationTestSupport {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("이메일로 회원 정보를 조회할 수 있다.")
    @Test
    void getMemberInfo() {
        //given
        Member member = createMember();

        //when
        MemberResponse response = accountService.getMemberInfo(member.getMemberKey());

        //then
        assertThat(response)
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