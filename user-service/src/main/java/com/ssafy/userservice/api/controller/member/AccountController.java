package com.ssafy.userservice.api.controller.member;

import com.ssafy.userservice.api.controller.ApiResponse;
import com.ssafy.userservice.api.controller.member.request.CheckEmailRequest;
import com.ssafy.userservice.api.controller.member.request.CheckNicknameRequest;
import com.ssafy.userservice.api.controller.member.response.MemberResponse;
import com.ssafy.userservice.api.service.member.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 계정 관련 API 컨트롤러
 *
 * @author 임우택
 */
@RequiredArgsConstructor
@RestController
@Slf4j
public class AccountController {

    private final AccountService accountService;

    /**
     * 계정 정보 조회 API
     *
     * @param memberKey 조회할 회원 고유키
     * @return 200 조회된 계정 정보
     */
    @GetMapping("/api/{memberKey}/info")
    public ApiResponse<MemberResponse> getMemberInfo(@PathVariable String memberKey) {
        log.debug("call AccountController#getMemberInfo");
        log.debug("memberKey={}", memberKey);

        MemberResponse response = accountService.getMemberInfo(memberKey);
        log.debug("MemberResponse={}", response);

        return ApiResponse.ok(response);
    }
}
