package com.ssafy.keywordservice.docs.articlekeyword;

import com.ssafy.keywordservice.api.controller.articlekeyword.ArticleKeywordController;
import com.ssafy.keywordservice.api.controller.articlekeyword.response.ArticleKeywordResponse;
import com.ssafy.keywordservice.api.controller.keyword.request.CreatedKeywordRequest;
import com.ssafy.keywordservice.api.service.articlekeyword.ArticleKeywordQueryService;
import com.ssafy.keywordservice.api.service.articlekeyword.ArticleKeywordService;
import com.ssafy.keywordservice.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ArticleKeywordControllerDocsTest extends RestDocsSupport {

    private final ArticleKeywordService articleKeywordService = mock(ArticleKeywordService.class);
    private final ArticleKeywordQueryService articleKeywordQueryService = mock(ArticleKeywordQueryService.class);

    @Override
    protected Object initController() {
        return new ArticleKeywordController(articleKeywordService, articleKeywordQueryService);
    }

    @DisplayName("뉴스 키워드 등록 API")
    @Test
    void createArticleKeyword() throws Exception {
        Long articleKey = 43578L;
        CreatedKeywordRequest request = CreatedKeywordRequest.builder()
            .word("돼지")
            .build();

        ArticleKeywordResponse response = ArticleKeywordResponse.builder()
            .keywordId(1L)
            .word("돼지")
            .build();

        given(articleKeywordService.createArticleKeyword(anyLong(), anyString()))
            .willReturn(response);

        mockMvc.perform(
                post("/keyword-service/api/{articleKey}/articles", articleKey)
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(document("create-article-keyword",
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
                        .description("키워드 PK"),
                    fieldWithPath("data.word").type(JsonFieldType.STRING)
                        .description("키워드 단어")
                )
            ));
    }

    @DisplayName("뉴스 키워드 조회 API")
    @Test
    void searchArticleKeyword() throws Exception {
        Long articleKey = 43578L;

        ArticleKeywordResponse response1 = ArticleKeywordResponse.builder()
            .keywordId(1L)
            .word("SSAFY")
            .build();

        ArticleKeywordResponse response2 = ArticleKeywordResponse.builder()
            .keywordId(2L)
            .word("삼성")
            .build();

        given(articleKeywordQueryService.getArticleKeywords(anyLong()))
            .willReturn(List.of(response1, response2));

        mockMvc.perform(
                get("/keyword-service/api/{articleKey}/articles", articleKey)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-article-keyword",
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
                    fieldWithPath("data[].word").type(JsonFieldType.STRING)
                        .description("키워드 단어")
                )
            ));
    }
}
