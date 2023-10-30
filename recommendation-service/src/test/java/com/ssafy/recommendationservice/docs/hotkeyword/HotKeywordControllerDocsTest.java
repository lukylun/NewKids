package com.ssafy.recommendationservice.docs.hotkeyword;

import com.ssafy.recommendationservice.api.controller.hotkeyword.HotKeywordController;
import com.ssafy.recommendationservice.api.controller.hotkeyword.response.LiveResponse;
import com.ssafy.recommendationservice.api.controller.hotkeyword.response.WordCloudResponse;
import com.ssafy.recommendationservice.api.service.hotkeyword.HotKeywordService;
import com.ssafy.recommendationservice.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDateTime;
import java.util.List;

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

public class HotKeywordControllerDocsTest extends RestDocsSupport {

    private final HotKeywordService hotKeywordService = mock(HotKeywordService.class);

    @Override
    protected Object initController() {
        return new HotKeywordController(hotKeywordService);
    }

    @DisplayName("워드 클라우드 핫이슈 키워드 조회 API")
    @Test
    void getLiveHotKeyword() throws Exception {
        LiveResponse response1 = LiveResponse.builder()
            .keywordId(1L)
            .keyword("돼지")
            .build();
        LiveResponse response2 = LiveResponse.builder()
            .keywordId(2L)
            .keyword("홍진식")
            .build();
        LiveResponse response3 = LiveResponse.builder()
            .keywordId(3L)
            .keyword("BBQ")
            .build();
        LiveResponse response4 = LiveResponse.builder()
            .keywordId(4L)
            .keyword("아이폰15")
            .build();
        LiveResponse response5 = LiveResponse.builder()
            .keywordId(5L)
            .keyword("맥북 air 15")
            .build();

        List<LiveResponse> responses = List.of(response1, response2, response3, response4, response5);

        given(hotKeywordService.getLiveHotKeyword(any(LocalDateTime.class)))
            .willReturn(responses);

        mockMvc.perform(
                get("/recommendation-service/api/hot-keyword/live")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-live-keyword",
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
                        .description("키워드 내용")
                )
            ));
    }

    @DisplayName("워드 클라우드 핫이슈 키워드 조회 API")
    @Test
    void getHotKeywordByWordCloud() throws Exception {
        WordCloudResponse response1 = WordCloudResponse.builder()
            .keywordId(1L)
            .text("돼지")
            .value(50)
            .build();
        WordCloudResponse response2 = WordCloudResponse.builder()
            .keywordId(2L)
            .text("홍진식")
            .value(40)
            .build();
        WordCloudResponse response3 = WordCloudResponse.builder()
            .keywordId(3L)
            .text("BBQ")
            .value(30)
            .build();
        WordCloudResponse response4 = WordCloudResponse.builder()
            .keywordId(4L)
            .text("아이폰15")
            .value(20)
            .build();
        WordCloudResponse response5 = WordCloudResponse.builder()
            .keywordId(5L)
            .text("맥북 air 15")
            .value(10)
            .build();

        List<WordCloudResponse> responses = List.of(response1, response2, response3, response4, response5);

        given(hotKeywordService.getCloudHotKeyword(any(LocalDateTime.class)))
            .willReturn(responses);

        mockMvc.perform(
                get("/recommendation-service/api/hot-keyword/cloud")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-cloud-keyword",
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
                    fieldWithPath("data[].text").type(JsonFieldType.STRING)
                        .description("키워드 내용"),
                    fieldWithPath("data[].value").type(JsonFieldType.NUMBER)
                        .description("키워드 볼륨")
                )
            ));
    }
}
