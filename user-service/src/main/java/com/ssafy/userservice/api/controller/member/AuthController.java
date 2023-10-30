package com.ssafy.userservice.api.controller.member;

import com.ssafy.userservice.api.controller.ApiResponse;
import com.ssafy.userservice.api.controller.member.request.AuthCheckEmailRequest;
import com.ssafy.userservice.api.controller.member.request.AuthEmailRequest;
import com.ssafy.userservice.api.controller.member.request.CheckEmailRequest;
import com.ssafy.userservice.api.controller.member.request.CheckNicknameRequest;
import com.ssafy.userservice.api.service.member.AuthService;
import com.ssafy.userservice.client.mail.EmailMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 인증 API 컨트롤러
 * @author 임우택
 */
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * 회원 가입시 이메일 중복 체크 API
     *
     * @param request 중복 체크할 이메일
     * @return 200 존재하면 true, 존재하지 않으면 false
     */
    @PostMapping("/duplication/email")
    public ApiResponse<Boolean> checkEmail(@Valid @RequestBody CheckEmailRequest request) {
        log.debug("call AccountController#checkEmail");
        log.debug("CheckEmailRequest={}", request);

        Boolean result = authService.checkEmail(request.getEmail());
        log.debug("result={}", result);

        return ApiResponse.ok(result);
    }

    /**
     * 회원 가입시 닉네임 중복 체크 API
     *
     * @param request 중복 체크할 닉네임
     * @return 200 존재하면 true, 존재하지 않으면 false
     */
    @PostMapping("/duplication/nickname")
    public ApiResponse<Boolean> checkNickname(@Valid @RequestBody CheckNicknameRequest request) {
        log.debug("call AccountController#checkNickname");
        log.debug("CheckNicknameRequest={}", request);

        Boolean result = authService.checkNickname(request.getNickname());
        log.debug("result={}", result);

        return ApiResponse.ok(result);
    }

    @PostMapping("/email")
    public ApiResponse<?> authEmail(@Valid @RequestBody AuthEmailRequest request) {
        EmailMessage emailMessage = EmailMessage.builder()
            .to(request.getEmail())
            .subject("[Newkids] 이메일 인증을 위한 인증 코드 발송")
            .build();

        authService.authEmail(emailMessage);

        return ApiResponse.ok(null);
    }

    @PostMapping("/email/check")
    public ApiResponse<?> checkAuthEmail(@Valid @RequestBody AuthCheckEmailRequest request) {
        authService.authCheckEmail(request.getEmail(), request.getAuthNumber());

        return ApiResponse.ok(null);
    }
}
