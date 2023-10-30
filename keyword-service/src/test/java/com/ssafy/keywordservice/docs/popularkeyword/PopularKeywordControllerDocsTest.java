package com.ssafy.keywordservice.docs.popularkeyword;

import com.ssafy.keywordservice.api.controller.popularkeyword.PopularKeywordController;
import com.ssafy.keywordservice.api.controller.popularkeyword.response.PopularKeywordResponse;
import com.ssafy.keywordservice.api.service.popularkeyword.PopularKeywordQueryService;
import com.ssafy.keywordservice.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;
import java.util.Random;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PopularKeywordControllerDocsTest extends RestDocsSupport {

    private final PopularKeywordQueryService popularKeywordQueryService = mock(PopularKeywordQueryService.class);

    @Override
    protected Object initController() {
        return new PopularKeywordController(popularKeywordQueryService);
    }

    @DisplayName("뉴스 키워드 조회 API")
    @Test
    void searchArticleKeyword() throws Exception {
        PopularKeywordResponse response1 = createResponse(1L, "돼지");
        PopularKeywordResponse response2 = createResponse(2L, "삼겹살");
        PopularKeywordResponse response3 = createResponse(3L, "목살");
        PopularKeywordResponse response4 = createResponse(4L, "배고파");
        PopularKeywordResponse response5 = createResponse(5L, "쌈장");

        List<PopularKeywordResponse> responses = List.of(response1, response2, response3, response4, response5);

        given(popularKeywordQueryService.getTopTenPopularKeyword())
            .willReturn(responses);

        mockMvc.perform(
                get("/keyword-service/api/popular")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-popular-keyword",
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER)
                        .description("코드"),
                    fieldWithPath("status").type(JsonFieldType.STRING)
                        .description("상태"),
                    fieldWithPath("message").type(JsonFieldType.STRING)
                        .description("메시지"),
                    fieldWithPath("data[]").type(JsonFieldType.ARRAY)
                        .description("응답 데이터"),
                    fieldWithPath("data[].keywordId").type(JsonFieldType.NUMBER)
                        .description("키워드 PK"),
                    fieldWithPath("data[].word").type(JsonFieldType.STRING)
                        .description("키워드 단어"),
                    fieldWithPath("data[].totalCount").type(JsonFieldType.NUMBER)
                        .description("키워드 총 조회수")
                )
            ));
    }

    private PopularKeywordResponse createResponse(long keywordId, String word) {
        int count = new Random().nextInt(100);
        return PopularKeywordResponse.builder()
            .keywordId(keywordId)
            .word(word)
            .totalCount(count)
            .build();
    }
}
