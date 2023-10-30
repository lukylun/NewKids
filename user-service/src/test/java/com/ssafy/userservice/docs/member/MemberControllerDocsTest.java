package com.ssafy.userservice.docs.member;

import com.ssafy.userservice.api.controller.member.MemberController;
import com.ssafy.userservice.api.controller.member.request.EditNicknameRequest;
import com.ssafy.userservice.api.controller.member.request.EditPasswordRequest;
import com.ssafy.userservice.api.controller.member.request.JoinRequest;
import com.ssafy.userservice.api.controller.member.request.WithdrawalRequest;
import com.ssafy.userservice.api.controller.member.response.JoinMemberResponse;
import com.ssafy.userservice.api.controller.member.response.MemberResponse;
import com.ssafy.userservice.api.service.member.MemberService;
import com.ssafy.userservice.api.service.member.dto.JoinMemberDto;
import com.ssafy.userservice.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberControllerDocsTest extends RestDocsSupport {

    private final MemberService memberService = mock(MemberService.class);

    @Override
    protected Object initController() {
        return new MemberController(memberService);
    }

    @DisplayName("회원 가입 API")
    @Test
    void join() throws Exception {
        JoinRequest request = JoinRequest.builder()
            .email("ssafy@ssafy.com")
            .password("ssafy1234!")
            .name("김싸피")
            .age(10)
            .nickname("광주C205")
            .build();

        JoinMemberResponse response = JoinMemberResponse.builder()
            .email("ssafy@ssafy.com")
            .name("김싸피")
            .age(10)
            .nickname("광주C205")
            .build();

        given(memberService.join(any(JoinMemberDto.class)))
            .willReturn(response);

        mockMvc.perform(
                post("/join")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(document("create-member",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("email").type(JsonFieldType.STRING)
                        .optional()
                        .description("계정 이메일"),
                    fieldWithPath("password").type(JsonFieldType.STRING)
                        .optional()
                        .description("계정 비밀번호"),
                    fieldWithPath("name").type(JsonFieldType.STRING)
                        .optional()
                        .description("이름"),
                    fieldWithPath("age").type(JsonFieldType.NUMBER)
                        .optional()
                        .description("나이"),
                    fieldWithPath("nickname").type(JsonFieldType.STRING)
                        .optional()
                        .description("닉네임")
                ),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER)
                        .description("코드"),
                    fieldWithPath("status").type(JsonFieldType.STRING)
                        .description("상태"),
                    fieldWithPath("message").type(JsonFieldType.STRING)
                        .description("메시지"),
                    fieldWithPath("data").type(JsonFieldType.OBJECT)
                        .description("응답데이터"),
                    fieldWithPath("data.email").type(JsonFieldType.STRING)
                        .description("계정 이메일"),
                    fieldWithPath("data.name").type(JsonFieldType.STRING)
                        .description("이름"),
                    fieldWithPath("data.age").type(JsonFieldType.NUMBER)
                        .description("나이"),
                    fieldWithPath("data.nickname").type(JsonFieldType.STRING)
                        .description("닉네임")
                )
            ));
    }

    @DisplayName("계정 비밀번호 변경 API")
    @Test
    void editPassword() throws Exception {
        EditPasswordRequest request = EditPasswordRequest.builder()
            .currentPwd("ssafy1234!")
            .newPwd("ssafyc205!")
            .build();

        MemberResponse response = MemberResponse.builder()
            .email("ssafy@ssafy.com")
            .name("김싸피")
            .age(10)
            .level(1)
            .exp(0)
            .nickname("광주C205")
            .build();

        given(memberService.editPassword(anyString(), anyString(), anyString()))
            .willReturn(response);

        mockMvc.perform(
                patch("/api/{memberKey}/password", UUID.randomUUID().toString())
                    .header("Authorization", "Bearer accessToken")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("edit-password",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("currentPwd").type(JsonFieldType.STRING)
                        .optional()
                        .description("현재 계정 비밀번호"),
                    fieldWithPath("newPwd").type(JsonFieldType.STRING)
                        .optional()
                        .description("변경할 계정 비밀번호")
                ),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER)
                        .description("코드"),
                    fieldWithPath("status").type(JsonFieldType.STRING)
                        .description("상태"),
                    fieldWithPath("message").type(JsonFieldType.STRING)
                        .description("메시지"),
                    fieldWithPath("data").type(JsonFieldType.OBJECT)
                        .description("응답 데이터"),
                    fieldWithPath("data.email").type(JsonFieldType.STRING)
                        .description("이메일"),
                    fieldWithPath("data.name").type(JsonFieldType.STRING)
                        .description("이름"),
                    fieldWithPath("data.age").type(JsonFieldType.NUMBER)
                        .description("나이"),
                    fieldWithPath("data.level").type(JsonFieldType.NUMBER)
                        .description("레벨"),
                    fieldWithPath("data.exp").type(JsonFieldType.NUMBER)
                        .description("경험치"),
                    fieldWithPath("data.nickname").type(JsonFieldType.STRING)
                        .description("닉네임")
                )
            ));
    }

    @DisplayName("계정 닉네임 변경 API")
    @Test
    void editNickname() throws Exception {
        EditNicknameRequest request = EditNicknameRequest.builder()
            .newNickname("광주2반")
            .build();

        MemberResponse response = MemberResponse.builder()
            .email("ssafy@ssafy.com")
            .name("김싸피")
            .age(10)
            .level(1)
            .exp(0)
            .nickname("광주2반")
            .build();

        given(memberService.editNickname(anyString(), anyString()))
            .willReturn(response);

        mockMvc.perform(
                patch("/api/{memberKey}/nickname", UUID.randomUUID().toString())
                    .header("Authorization", "Bearer accessToken")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("edit-nickname",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("newNickname").type(JsonFieldType.STRING)
                        .optional()
                        .description("변경할 닉네임")
                ),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER)
                        .description("코드"),
                    fieldWithPath("status").type(JsonFieldType.STRING)
                        .description("상태"),
                    fieldWithPath("message").type(JsonFieldType.STRING)
                        .description("메시지"),
                    fieldWithPath("data").type(JsonFieldType.OBJECT)
                        .description("응답 데이터"),
                    fieldWithPath("data.email").type(JsonFieldType.STRING)
                        .description("이메일"),
                    fieldWithPath("data.name").type(JsonFieldType.STRING)
                        .description("이름"),
                    fieldWithPath("data.age").type(JsonFieldType.NUMBER)
                        .description("나이"),
                    fieldWithPath("data.level").type(JsonFieldType.NUMBER)
                        .description("레벨"),
                    fieldWithPath("data.exp").type(JsonFieldType.NUMBER)
                        .description("경험치"),
                    fieldWithPath("data.nickname").type(JsonFieldType.STRING)
                        .description("닉네임")
                )
            ));
    }

    @DisplayName("회원 탈퇴 API")
    @Test
    void withdrawal() throws Exception {
        WithdrawalRequest request = WithdrawalRequest.builder()
            .password("ssafy1234!")
            .build();

        given(memberService.withdrawal(anyString(), anyString()))
            .willReturn(true);

        mockMvc.perform(
                delete("/api/{memberKey}/withdrawal", UUID.randomUUID().toString())
                    .header("Authorization", "Bearer accessToken")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("remove-member",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("password").type(JsonFieldType.STRING)
                        .optional()
                        .description("계정 비밀번호")
                ),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER)
                        .description("코드"),
                    fieldWithPath("status").type(JsonFieldType.STRING)
                        .description("상태"),
                    fieldWithPath("message").type(JsonFieldType.STRING)
                        .description("메시지"),
                    fieldWithPath("data").type(JsonFieldType.BOOLEAN)
                        .description("중복 여부(true: 사용불가, false: 사용가능)")
                )
            ));
    }
}
