package com.ssafy.userservice.api.service.member;

import com.ssafy.userservice.api.controller.member.response.MemberResponse;
import com.ssafy.userservice.domain.member.Member;
import com.ssafy.userservice.domain.member.repository.MemberQueryRepository;
import com.ssafy.userservice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AccountService implements UserDetailsService{

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> findMember = memberRepository.findByEmail(email);

        if (findMember.isEmpty()) {
            throw new UsernameNotFoundException("등록되지 않은 사용자입니다.");
        }

        Member member = findMember.get();
        return new User(member.getEmail(), member.getEncryptedPwd(),
            true, true, true, true,
            new ArrayList<>());
    }

    /**
     * 계정 정보 조회
     *
     * @param memberKey 조회할 회원 고유키
     * @return 조회된 계정 정보
     */
    public MemberResponse getMemberInfo(String memberKey) {
        return memberQueryRepository.findByMemberKey(memberKey)
            .orElseThrow(NoSuchElementException::new);
    }

    public Member getUserDetailsByEmail(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);

        if (findMember.isEmpty()) {
            throw new UsernameNotFoundException("등록되지 않은 사용자입니다.");
        }

        return findMember.get();
    }
}
