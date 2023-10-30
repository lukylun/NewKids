package com.ssafy.keywordservice.docs.keyword;

import com.ssafy.keywordservice.api.controller.keyword.KeywordController;
import com.ssafy.keywordservice.api.controller.keyword.request.CreatedKeywordRequest;
import com.ssafy.keywordservice.api.controller.keyword.response.KeywordResponse;
import com.ssafy.keywordservice.api.service.keyword.KeywordQueryService;
import com.ssafy.keywordservice.api.service.keyword.KeywordService;
import com.ssafy.keywordservice.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class KeywordControllerDocsTest extends RestDocsSupport {

    private final KeywordService keywordService = mock(KeywordService.class);
    private final KeywordQueryService keywordQueryService = mock(KeywordQueryService.class);

    @Override
    protected Object initController() {
        return new KeywordController(keywordService, keywordQueryService);
    }

    @DisplayName("키워드 등록 API")
    @Test
    void createKeyword() throws Exception {

        CreatedKeywordRequest request = CreatedKeywordRequest.builder()
            .word("돼지")
            .build();

        KeywordResponse response = KeywordResponse.builder()
            .keywordId(1L)
            .word("돼지")
            .createdDate(LocalDateTime.of(2023, 9, 20, 16, 26))
            .build();

        BDDMockito.given(keywordService.createKeyword(anyString()))
            .willReturn(response);

        mockMvc.perform(
                post("/keyword-service/api")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(document("create-keyword",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("word").type(JsonFieldType.STRING)
                        .optional()
                        .description("등록할 단어")
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
                    fieldWithPath("data.keywordId").type(JsonFieldType.NUMBER)
                        .description("등록된 키워드 PK"),
                    fieldWithPath("data.word").type(JsonFieldType.STRING)
                        .description("등록된 키워드"),
                    fieldWithPath("data.createdDate").type(JsonFieldType.STRING)
                        .description("등록된 시간")
                )
            ));
    }
}
