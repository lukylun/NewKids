package com.ssafy.userservice.api.service.member;

import com.ssafy.userservice.api.controller.member.response.JoinMemberResponse;
import com.ssafy.userservice.api.controller.member.response.MemberLevelResponse;
import com.ssafy.userservice.api.controller.member.response.MemberResponse;
import com.ssafy.userservice.api.service.member.dto.JoinMemberDto;
import com.ssafy.userservice.api.service.member.exception.DuplicateException;
import com.ssafy.userservice.api.service.member.exception.MismatchException;
import com.ssafy.userservice.domain.member.Member;
import com.ssafy.userservice.domain.member.repository.MemberQueryRepository;
import com.ssafy.userservice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * 회원 서비스
 *
 * @author 임우택
 */
@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 회원 가입
     *
     * @param dto 회원의 정보
     * @return 가입을 성공한 회원의 기본 정보
     * @throws DuplicateException 이미 사용중인 이메일, 닉네임인 경우
     */
    public JoinMemberResponse join(JoinMemberDto dto) {
        duplicationCheckByEmail(dto.getEmail());
        duplicationCheckByNickname(dto.getNickname());

        Member savedMember = createMember(dto);

        return JoinMemberResponse.of(savedMember);
    }

    /**
     * 회원 탈퇴
     *
     * @param memberKey 탈퇴할 회원 고유키
     * @param password 탈퇴할 계정 비밀번호
     * @return 탈퇴 성공 여부(성공: true, 실패: false)
     * @throws NoSuchElementException 계정 이메일이 일치하는 회원이 존재하지 않을 경우
     */
    public boolean withdrawal(String memberKey, String password) {
        Member member = getMemberEntity(memberKey);

        if (isMatchPassword(password, member)) {
            member.deActive();
            return true;
        }

        return false;
    }

    /**
     * 계정 비밀번호 변경
     *
     * @param memberKey 변경할 회원 고유키
     * @param currentPwd 현재 비밀번호
     * @param newPwd 변경할 비밀번호
     * @return 변경된 계정 정보
     * @throws NoSuchElementException 계정 이메일이 일치하는 회원이 존재하지 않을 경우
     * @throws MismatchException 현재 비밀번호가 불일치하는 경우
     */
    public MemberResponse editPassword(String memberKey, String currentPwd, String newPwd) {
        Member member = getMemberEntity(memberKey);

        if (!isMatchPassword(currentPwd, member)) {
            throw new MismatchException("현재 비밀번호가 일치하지 않습니다.");
        }

        String encryptedPwd = passwordEncoder.encode(newPwd);
        member.editEncryptedPwd(encryptedPwd);
        return MemberResponse.of(member);
    }

    /**
     * 계정 닉네임 변경
     *
     * @param memberKey 변경할 회원 고유키
     * @param newNickname 변경할 닉네임
     * @return 변경된 계정 정보
     * @throws NoSuchElementException 계정 이메일이 일치하는 회원이 존재하지 않을 경우
     */
    public MemberResponse editNickname(String memberKey, String newNickname) {
        Member member = getMemberEntity(memberKey);

        member.editNickname(newNickname);
        return MemberResponse.of(member);
    }

    public MemberLevelResponse increaseExp(String memberKey, int exp) {
        Member member = getMemberEntity(memberKey);

        member.increaseExp(exp);

        return MemberLevelResponse.of(member);
    }

    /**
     * 이메일 중복 체크
     *
     * @param email 중복 체크할 이메일
     * @throws DuplicateException 이미 사용중인 이메일인 경우
     */
    private void duplicationCheckByEmail(String email) {
        boolean checkEmail = memberQueryRepository.existEmail(email);
        if (checkEmail) {
            throw new DuplicateException("이미 사용중인 이메일입니다.");
        }
    }

    /**
     * 닉네임 중복 체크
     *
     * @param nickname 중복 체크할 닉네임
     * @throws DuplicateException 이미 사용중인 닉네임인 경우
     */
    private void duplicationCheckByNickname(String nickname) {
        boolean checkNickname = memberQueryRepository.existNickname(nickname);
        if (checkNickname) {
            throw new DuplicateException("이미 사용중인 닉네임입니다.");
        }
    }

    /**
     * 회원 엔티티 생성 및 DB 저장
     *
     * @param dto 회원 정보
     * @return 가입된 회원 엔티티
     */
    private Member createMember(JoinMemberDto dto) {
        String encryptedPwd = passwordEncoder.encode(dto.getPassword());
        String memberKey = UUID.randomUUID().toString();
        Member member = dto.toEntity(encryptedPwd, memberKey);
        return memberRepository.save(member);
    }

    /**
     * 계정 이메일로 회원 엔티티 조회
     *
     * @param memberKey 조회할 계정 이메일
     * @return 조회된 회원 엔티티
     * @throws NoSuchElementException 계정 이메일이 일치하는 회원이 존재하지 않을 경우
     */
    private Member getMemberEntity(String memberKey) {
        return memberRepository.findByMemberKey(memberKey)
            .orElseThrow(NoSuchElementException::new);
    }

    /**
     * 비밀번호 일치 여부
     *
     * @param password 입력받은 계정 비밀번호
     * @param member 일치 여부를 판별할 회원 엔티티
     * @return 비밀번호 일치 여부(일치: true, 불일치: false)
     */
    private boolean isMatchPassword(String password, Member member) {
        return passwordEncoder.matches(password, member.getEncryptedPwd());
    }
}
