package com.ssafy.userservice.docs.member;

import com.ssafy.userservice.api.controller.member.MemberClientController;
import com.ssafy.userservice.api.controller.member.request.IncreaseExpRequest;
import com.ssafy.userservice.api.controller.member.response.MemberLevelResponse;
import com.ssafy.userservice.api.service.member.MemberService;
import com.ssafy.userservice.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.UUID;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberClientControllerDocsTest extends RestDocsSupport {

    private final MemberService memberService = mock(MemberService.class);

    @Override
    protected Object initController() {
        return new MemberClientController(memberService);
    }

    @DisplayName("회원 경험치 증가 API")
    @Test
    void increaseExp() throws Exception {
        String memberKey = UUID.randomUUID().toString();

        IncreaseExpRequest request = IncreaseExpRequest.builder()
            .exp(70)
            .build();

        MemberLevelResponse response = MemberLevelResponse.builder()
            .level(13)
            .exp(20)
            .build();

        given(memberService.increaseExp(anyString(), anyInt()))
            .willReturn(response);

        mockMvc.perform(
            post("/client/{memberKey}", memberKey)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("increase-exp",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("exp").type(JsonFieldType.NUMBER)
                        .optional()
                        .description("획득한 경험치")
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
                    fieldWithPath("data.level").type(JsonFieldType.NUMBER)
                        .description("증가 후 레벨"),
                    fieldWithPath("data.exp").type(JsonFieldType.NUMBER)
                        .description("증가 후 경험치")
                )
            ));
    }
}
