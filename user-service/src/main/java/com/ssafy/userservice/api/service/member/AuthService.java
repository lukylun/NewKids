package com.ssafy.userservice.api.service.member;

import com.ssafy.userservice.client.mail.EmailMessage;
import com.ssafy.userservice.client.mail.MailSendClient;
import com.ssafy.userservice.domain.member.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * 인증 서비스
 *
 * @author 임우택
 */
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuthService {

    private final MemberQueryRepository memberQueryRepository;
    private final MailSendClient mailSendClient;
    private final RedisTemplate<String, String> redisTemplate;

    /**
     * 이메일 중복 체크
     *
     * @param email 중복 체크할 대상 이메일
     * @return 존재하면 true, 존재하지 않으면 false
     */
    public boolean checkEmail(String email) {
        return memberQueryRepository.existEmail(email);
    }

    /**
     * 닉네임 중복 체크
     *
     * @param nickname 중복 체크할 대상 닉네임
     * @return 존재하면 true, 존재하지 않으면 false
     */
    public boolean checkNickname(String nickname) {
        return memberQueryRepository.existNickname(nickname);
    }

    /**
     * 이메일 인증 번호 발송
     *
     * @param emailMessage 발송할 이메일 정보
     */
    public void authEmail(EmailMessage emailMessage) {
        String authNumber = mailSendClient.sendEmail(emailMessage, "email");
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        valueOperations.set(emailMessage.getTo(), authNumber, 3, TimeUnit.MINUTES);
    }

    /**
     * 이메일 인증 번호 확인
     *
     * @param email      인증할 이메일
     * @param authNumber 발급된 이메일
     * @throws IllegalArgumentException 인증에 실패한 경우
     */
    public void authCheckEmail(String email, String authNumber) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String storeAuthNumber = operations.get(email);

        if (!authNumber.equals(storeAuthNumber)) {
            throw new IllegalArgumentException("인증 실패");
        }

        redisTemplate.delete(email);
    }
}
