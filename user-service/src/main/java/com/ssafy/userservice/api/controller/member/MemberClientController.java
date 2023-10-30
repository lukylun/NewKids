package com.ssafy.userservice.api.controller.member;

import com.ssafy.userservice.api.controller.ApiResponse;
import com.ssafy.userservice.api.controller.member.request.IncreaseExpRequest;
import com.ssafy.userservice.api.controller.member.response.MemberLevelResponse;
import com.ssafy.userservice.api.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/client")
public class MemberClientController {

    private final MemberService memberService;

    @PostMapping("/{memberKey}")
    public ApiResponse<MemberLevelResponse> increaseExp(@PathVariable String memberKey, @RequestBody IncreaseExpRequest request) {
        log.debug("MemberClientController#increaseExp");
        log.debug("memberKey={}", memberKey);

        MemberLevelResponse response = memberService.increaseExp(memberKey, request.getExp());
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }
}
