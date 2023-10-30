package com.ssafy.userservice.api.controller.member;

import com.ssafy.userservice.api.controller.ApiResponse;
import com.ssafy.userservice.api.controller.member.request.EditNicknameRequest;
import com.ssafy.userservice.api.controller.member.request.EditPasswordRequest;
import com.ssafy.userservice.api.controller.member.request.JoinRequest;
import com.ssafy.userservice.api.controller.member.request.WithdrawalRequest;
import com.ssafy.userservice.api.controller.member.response.JoinMemberResponse;
import com.ssafy.userservice.api.controller.member.response.MemberResponse;
import com.ssafy.userservice.api.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 회원 API 컨트롤러
 *
 * @author 임우택
 */
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/")
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 가입 API
     *
     * @param request 가입할 회원의 정보
     * @return 가입된 회원의 정보
     */
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<JoinMemberResponse> join(@Valid @RequestBody JoinRequest request) {
        log.debug("call MemberController#join");
        log.debug("JoinRequest={}", request);

        JoinMemberResponse response = memberService.join(request.toJoinMemberDto());
        log.debug("JoinMemberResponse={}", response);

        return ApiResponse.created(response);
    }

    /**
     * 계정 비밀번호 변경 API
     *
     * @param memberKey 변경할 계정의 고유키
     * @param request 변경할 계정 비밀번호 정보
     * @return 비밀번호 변경된 계정 정보
     */
    @PatchMapping("/api/{memberKey}/password")
    public ApiResponse<MemberResponse> editPassword(
        @PathVariable String memberKey,
        @Valid @RequestBody EditPasswordRequest request
    ) {
        log.debug("call MemberController#editPassword");
        log.debug("EditPasswordRequest={}", request);
        log.debug("memberKey={}", memberKey);

        MemberResponse response = memberService.editPassword(memberKey, request.getCurrentPwd(), request.getNewPwd());
        log.debug("MemberResponse={}", response);

        return ApiResponse.ok(response);
    }

    /**
     * 계정 닉네임 변경 API
     *
     * @param memberKey 변경할 계정의 고유키
     * @param request 변경할 계정 닉네임 정보
     * @return 닉네임 변경된 계정 정보
     */
    @PatchMapping("/api/{memberKey}/nickname")
    public ApiResponse<MemberResponse> editNickname(
        @PathVariable String memberKey,
        @Valid @RequestBody EditNicknameRequest request
    ) {
        log.debug("call MemberController#editNickname");
        log.debug("EditPasswordRequest={}", request);
        log.debug("memberKey={}", memberKey);

        MemberResponse response = memberService.editNickname(memberKey, request.getNewNickname());
        log.debug("MemberResponse={}", response);

        return ApiResponse.ok(response);
    }

    /**
     * 회원 탈퇴 API
     *
     * @param request 탈퇴할 계정 비밀번호
     * @return 탈퇴 성공 여부(성공: true, 실패: false)
     */
    @DeleteMapping("/api/{memberKey}/withdrawal")
    public ApiResponse<Boolean> withdrawal(
        @PathVariable String memberKey,
        @Valid @RequestBody WithdrawalRequest request
    ) {
        log.debug("call MemberController#withdrawal");
        log.debug("WithdrawalRequest={}", request);
        log.debug("memberKey={}", memberKey);

        Boolean result = memberService.withdrawal(memberKey, request.getPassword());
        log.debug("result={}", result);

        return ApiResponse.ok(result);
    }
}
