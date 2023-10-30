package com.ssafy.userservice.api.service.member;

import com.ssafy.userservice.IntegrationTestSupport;
import com.ssafy.userservice.api.controller.member.response.JoinMemberResponse;
import com.ssafy.userservice.api.controller.member.response.MemberResponse;
import com.ssafy.userservice.api.service.member.dto.JoinMemberDto;
import com.ssafy.userservice.api.service.member.exception.DuplicateException;
import com.ssafy.userservice.api.service.member.exception.MismatchException;
import com.ssafy.userservice.domain.member.Member;
import com.ssafy.userservice.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 회원 서비스 테스트
 *
 * @author 임우택
 */
class MemberServiceTest extends IntegrationTestSupport {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @DisplayName("회원 가입시 입력된 이메일이 이미 사용 중 이라면 예외가 발생한다.")
    @Test
    void joinWithDuplicatedEmail() {
        //given
        JoinMemberDto dto = JoinMemberDto.builder()
            .email("ssfay@ssafy.com")
            .password("encryptedPwd")
            .name("김싸피")
            .age(10)
            .nickname("광주C205")
            .build();
        Member member = createMember(dto.getEmail(), "광주");

        //when //then
        assertThatThrownBy(() -> memberService.join(dto))
            .isInstanceOf(DuplicateException.class)
            .hasMessage("이미 사용중인 이메일입니다.");
    }

    @DisplayName("회원 가입시 입력된 닉네임이 이미 사용 중 이라면 예외가 발생한다.")
    @Test
    void joinWithDuplicatedNickname() {
        //given
        JoinMemberDto dto = JoinMemberDto.builder()
            .email("ssfay@ssafy.com")
            .password("encryptedPwd")
            .name("김싸피")
            .age(10)
            .nickname("광주C205")
            .build();
        Member member = createMember("newssafy@ssafy.com", dto.getNickname());

        //when //then
        assertThatThrownBy(() -> memberService.join(dto))
            .isInstanceOf(DuplicateException.class)
            .hasMessage("이미 사용중인 닉네임입니다.");
    }

    @DisplayName("모든 사용자는 회원 가입을 할 수 있다.")
    @Test
    void join() {
        //given
        JoinMemberDto dto = JoinMemberDto.builder()
            .email("ssafy@ssafy.com")
            .password("encryptedPwd")
            .name("김싸피")
            .age(10)
            .nickname("광주C205")
            .build();

        //when
        JoinMemberResponse response = memberService.join(dto);

        //then
        assertThat(response)
            .extracting("email", "name", "age", "nickname")
            .containsExactlyInAnyOrder("ssafy@ssafy.com", "김싸피", 10, "광주C205");
    }

    @DisplayName("계정 비밀번호가 불일치하는 경우 회원 탈퇴를 할 수 없다.")
    @Test
    void withdrawalWithException() {
        //given
        Member member = createMember("ssafy@ssafy.com", "광주C205");

        //when
        boolean result = memberService.withdrawal(member.getMemberKey(), "ssafy1111!");

        //then
        assertThat(result).isFalse();
        Optional<Member> findMember = memberRepository.findById(member.getId());
        assertThat(findMember).isPresent();
        assertThat(findMember.get().getActive()).isTrue();
    }

    @DisplayName("계정 비밀번호가 일치하는 경우 회원 탈퇴를 할 수 있다.")
    @Test
    void withdrawal() {
        //given
        Member member = createMember("ssafy@ssafy.com", "광주C205");

        //when
        boolean result = memberService.withdrawal(member.getMemberKey(), "ssafy1234!");

        //then
        assertThat(result).isTrue();
        Optional<Member> findMember = memberRepository.findById(member.getId());
        assertThat(findMember).isPresent();
        assertThat(findMember.get().getActive()).isFalse();
    }

    @DisplayName("비밀번호 변경시 현재 비밀번호가 불일치하면 예외가 발생한다.")
    @Test
    void editPasswordWithCurrentPwd() {
        //given
        Member member = createMember("ssafy@ssafy.com", "광주C205");

        //when //then
        assertThatThrownBy(() -> memberService.editPassword(member.getMemberKey(), "ssafy1111!", "ssafyc205!"))
            .isInstanceOf(MismatchException.class)
            .hasMessage("현재 비밀번호가 일치하지 않습니다.");
    }

    @DisplayName("비밀번호 변경시 현재 비밀번호가 일치하면 변경할 수 있다.")
    @Test
    void editPassword() {
        //given
        Member member = createMember("ssafy@ssafy.com", "광주C205");

        //when
        MemberResponse response = memberService.editPassword(member.getMemberKey(), "ssafy1234!", "ssafyc205!");

        //then
        Optional<Member> findMember = memberRepository.findById(member.getId());
        assertThat(findMember).isPresent();
        assertThat(passwordEncoder.matches("ssafyc205!", findMember.get().getEncryptedPwd())).isTrue();
    }

    @DisplayName("회원은 계정 닉네임을 변경할 수 있다.")
    @Test
    void editNickname() {
        //given
        Member member = createMember("ssafy@ssafy.com", "광주C205");

        //when
        MemberResponse response = memberService.editNickname(member.getMemberKey(), "광주2반");

        //then
        Optional<Member> findMember = memberRepository.findById(member.getId());
        assertThat(findMember).isPresent();
        assertThat(findMember.get().getNickname()).isEqualTo("광주2반");
    }

    private Member createMember(String email, String nickname) {
        Member member = Member.builder()
            .memberKey(UUID.randomUUID().toString())
            .email(email)
            .encryptedPwd(passwordEncoder.encode("ssafy1234!"))
            .name("김싸피")
            .age(10)
            .level(1)
            .exp(0)
            .nickname(nickname)
            .active(true)
            .build();
        return memberRepository.save(member);
    }
}
