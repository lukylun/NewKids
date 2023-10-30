package com.ssafy.keywordservice.docs.keywordsearch;

import com.ssafy.keywordservice.api.controller.keywordsearch.KeywordSearchController;
import com.ssafy.keywordservice.api.controller.keywordsearch.response.KeywordSearchResponse;
import com.ssafy.keywordservice.api.service.keywordsearch.KeywordSearchQueryService;
import com.ssafy.keywordservice.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class KeywordSearchControllerDocsTest extends RestDocsSupport {

    private final KeywordSearchQueryService keywordSearchQueryService = mock(KeywordSearchQueryService.class);

    @Override
    protected Object initController() {
        return new KeywordSearchController(keywordSearchQueryService);
    }

    @DisplayName("나의 키워드 Top 5 조회 API")
    @Test
    void getMyKeywordSearch() throws Exception {
        String memberKey = UUID.randomUUID().toString();

        KeywordSearchResponse response1 = KeywordSearchResponse.builder()
            .keywordId(1L)
            .keyword("나의 키워드 1")
            .count(100)
            .build();
        KeywordSearchResponse response2 = KeywordSearchResponse.builder()
            .keywordId(2L)
            .keyword("나의 키워드 2")
            .count(90)
            .build();
        KeywordSearchResponse response3 = KeywordSearchResponse.builder()
            .keywordId(3L)
            .keyword("나의 키워드 3")
            .count(80)
            .build();
        KeywordSearchResponse response4 = KeywordSearchResponse.builder()
            .keywordId(4L)
            .keyword("나의 키워드 4")
            .count(70)
            .build();
        KeywordSearchResponse response5 = KeywordSearchResponse.builder()
            .keywordId(5L)
            .keyword("나의 키워드 5")
            .count(60)
            .build();

        List<KeywordSearchResponse> responses = List.of(response1, response2, response3, response4, response5);

        given(keywordSearchQueryService.getMyKeywordSearch(anyString()))
            .willReturn(responses);

        mockMvc.perform(
            get("/keyword-service/api/my/{memberKey}", memberKey)
        )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-my-keyword",
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER)
                        .description("코드"),
                    fieldWithPath("status").type(JsonFieldType.STRING)
                        .description("상태"),
                    fieldWithPath("message").type(JsonFieldType.STRING)
                        .description("메시지"),
                    fieldWithPath("data").type(JsonFieldType.ARRAY)
                        .description("응답 데이터"),
                    fieldWithPath("data[].keywordId").type(JsonFieldType.NUMBER)
                        .description("키워드 PK"),
                    fieldWithPath("data[].keyword").type(JsonFieldType.STRING)
                        .description("키워드 단어"),
                    fieldWithPath("data[].count").type(JsonFieldType.NUMBER)
                        .description("키워드 조회수")
                )
            ));
    }
}
