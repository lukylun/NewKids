package com.ssafy.userservice.docs.member;

import com.ssafy.userservice.api.controller.member.AccountController;
import com.ssafy.userservice.api.controller.member.response.MemberResponse;
import com.ssafy.userservice.api.service.member.AccountService;
import com.ssafy.userservice.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.UUID;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerDocsTest extends RestDocsSupport {

    private final AccountService accountService = mock(AccountService.class);

    @Override
    protected Object initController() {
        return new AccountController(accountService);
    }

    @DisplayName("계정 정보 조회 API")
    @Test
    void getMemberInfo() throws Exception {

        MemberResponse response = MemberResponse.builder()
            .email("ssafy@ssafy.com")
            .name("김싸피")
            .age(10)
            .level(2)
            .exp(50)
            .nickname("광주")
            .build();

        given(accountService.getMemberInfo(anyString()))
            .willReturn(response);

        mockMvc.perform(
                get("/api/{memberKey}/info", UUID.randomUUID().toString())
                    .header("Authorization", "Bearer accessToken")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-account",
                preprocessResponse(prettyPrint()),
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
}
