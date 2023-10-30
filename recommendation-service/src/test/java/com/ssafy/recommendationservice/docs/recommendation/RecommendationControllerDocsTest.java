package com.ssafy.recommendationservice.docs.recommendation;

import com.ssafy.recommendationservice.api.controller.recommendation.RecommendationController;
import com.ssafy.recommendationservice.api.controller.recommendation.response.AnotherArticleRecommendationResponse;
import com.ssafy.recommendationservice.api.controller.recommendation.response.PeerAgeRecommendationResponse;
import com.ssafy.recommendationservice.api.service.article.ArticleLogQueryService;
import com.ssafy.recommendationservice.api.service.recommendation.RecommendationService;
import com.ssafy.recommendationservice.client.response.ArticleResponse;
import com.ssafy.recommendationservice.client.response.TempResponse;
import com.ssafy.recommendationservice.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RecommendationControllerDocsTest extends RestDocsSupport {

    private final RecommendationService recommendationService = mock(RecommendationService.class);
    private final ArticleLogQueryService articleLogQueryService = mock(ArticleLogQueryService.class);

    @Override
    protected Object initController() {
        return new RecommendationController(recommendationService, articleLogQueryService);
    }

    @DisplayName("메인 뉴스 기사 추천 API")
    @Test
    void getMainRecommendation() throws Exception {
        ArticleResponse response1 = ArticleResponse.builder()
            .articleId(1L)
            .title("메인 뉴스 기사 추천 1")
            .thumbnailImg("http://thumbnailImg1.jpg")
            .build();
        ArticleResponse response2 = ArticleResponse.builder()
            .articleId(2L)
            .title("메인 뉴스 기사 추천 2")
            .thumbnailImg("http://thumbnailImg2.jpg")
            .build();
        ArticleResponse response3 = ArticleResponse.builder()
            .articleId(3L)
            .title("메인 뉴스 기사 추천 3")
            .thumbnailImg("http://thumbnailImg3.jpg")
            .build();
        ArticleResponse response4 = ArticleResponse.builder()
            .articleId(4L)
            .title("메인 뉴스 기사 추천 4")
            .thumbnailImg("http://thumbnailImg4.jpg")
            .build();
        ArticleResponse response5 = ArticleResponse.builder()
            .articleId(5L)
            .title("메인 뉴스 기사 추천 5")
            .thumbnailImg("http://thumbnailImg5.jpg")
            .build();
        List<ArticleResponse> responses = List.of(response1, response2, response3, response4, response5);

        given(articleLogQueryService.getHotArticle())
            .willReturn(responses);

        mockMvc.perform(
                get("/recommendation-service/api/main")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-main-recommendation",
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
                    fieldWithPath("data[].articleId").type(JsonFieldType.NUMBER)
                        .description("뉴스 기사 PK"),
                    fieldWithPath("data[].title").type(JsonFieldType.STRING)
                        .description("뉴스 기사 제목"),
                    fieldWithPath("data[].thumbnailImg").type(JsonFieldType.STRING)
                        .description("뉴스 기사 썸네일")
                )
            ));
    }

    @DisplayName("또래 뉴스 기사 추천 API")
    @Test
    void getPeerAgeRecommendation() throws Exception {
        TempResponse response1 = TempResponse.builder()
            .articleId(1L)
            .title("또래 뉴스 기사 추천 1")
            .thumbnailImg("http://thumbnailImg1.jpg")
            .writer("누군가")
            .publishedDate(LocalDateTime.now())
            .build();
        TempResponse response2 = TempResponse.builder()
            .articleId(2L)
            .title("또래 뉴스 기사 추천 2")
            .thumbnailImg("http://thumbnailImg2.jpg")
            .writer("누군가")
            .publishedDate(LocalDateTime.now())
            .build();
        TempResponse response3 = TempResponse.builder()
            .articleId(3L)
            .title("또래 뉴스 기사 추천 3")
            .thumbnailImg("http://thumbnailImg3.jpg")
            .writer("누군가")
            .publishedDate(LocalDateTime.now())
            .build();
        TempResponse response4 = TempResponse.builder()
            .articleId(4L)
            .title("또래 뉴스 기사 추천 4")
            .thumbnailImg("http://thumbnailImg4.jpg")
            .writer("누군가")
            .publishedDate(LocalDateTime.now())
            .build();
        TempResponse response5 = TempResponse.builder()
            .articleId(5L)
            .title("또래 뉴스 기사 추천 5")
            .thumbnailImg("http://thumbnailImg5.jpg")
            .writer("누군가")
            .publishedDate(LocalDateTime.now())
            .build();
        List<TempResponse> responses = List.of(response1, response2, response3, response4, response5);

        given(recommendationService.getPeerAgeRecommendation())
            .willReturn(responses);

        mockMvc.perform(
                get("/recommendation-service/api/peer-age")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-peer-age-recommendation",
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
                    fieldWithPath("data[].articleId").type(JsonFieldType.NUMBER)
                        .description("뉴스 기사 PK"),
                    fieldWithPath("data[].title").type(JsonFieldType.STRING)
                        .description("뉴스 기사 제목"),
                    fieldWithPath("data[].thumbnailImg").type(JsonFieldType.STRING)
                        .description("뉴스 기사 썸네일"),
                    fieldWithPath("data[].writer").type(JsonFieldType.STRING)
                        .description("뉴스 기사 작성자"),
                    fieldWithPath("data[].publishedDate").type(JsonFieldType.ARRAY)
                        .description("뉴스 기사 작성일")
                )
            ));
    }

    @DisplayName("다른 기사 추천 API")
    @Test
    void getAnotherArticleRecommendation() throws Exception {
        AnotherArticleRecommendationResponse response1 = AnotherArticleRecommendationResponse.builder()
            .articleId(1L)
            .title("다른 뉴스 기사 추천 1")
            .thumbnailImg("http://thumbnailImg1.jpg")
            .build();
        AnotherArticleRecommendationResponse response2 = AnotherArticleRecommendationResponse.builder()
            .articleId(2L)
            .title("다른 뉴스 기사 추천 2")
            .thumbnailImg("http://thumbnailImg2.jpg")
            .build();
        AnotherArticleRecommendationResponse response3 = AnotherArticleRecommendationResponse.builder()
            .articleId(3L)
            .title("다른 뉴스 기사 추천 3")
            .thumbnailImg("http://thumbnailImg3.jpg")
            .build();
        AnotherArticleRecommendationResponse response4 = AnotherArticleRecommendationResponse.builder()
            .articleId(4L)
            .title("다른 뉴스 기사 추천 4")
            .thumbnailImg("http://thumbnailImg4.jpg")
            .build();
        AnotherArticleRecommendationResponse response5 = AnotherArticleRecommendationResponse.builder()
            .articleId(5L)
            .title("다른 뉴스 기사 추천 5")
            .thumbnailImg("http://thumbnailImg5.jpg")
            .build();
        AnotherArticleRecommendationResponse response6 = AnotherArticleRecommendationResponse.builder()
            .articleId(6L)
            .title("다른 뉴스 기사 추천 6")
            .thumbnailImg("http://thumbnailImg6.jpg")
            .build();

        given(recommendationService.getAnotherArticleRecommendation(anyLong()))
            .willReturn(List.of(response1, response2, response3, response4, response5, response6));

        mockMvc.perform(
                get("/recommendation-service/api/another-article")
                    .param("articleId", "1")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-another-article-recommendation",
                preprocessResponse(prettyPrint()),
                requestParameters(
                    parameterWithName("articleId")
                        .description("현재 읽고 있는 기사 PK")
                ),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER)
                        .description("코드"),
                    fieldWithPath("status").type(JsonFieldType.STRING)
                        .description("상태"),
                    fieldWithPath("message").type(JsonFieldType.STRING)
                        .description("메시지"),
                    fieldWithPath("data").type(JsonFieldType.ARRAY)
                        .description("응답 데이터"),
                    fieldWithPath("data[].articleId").type(JsonFieldType.NUMBER)
                        .description("뉴스 기사 PK"),
                    fieldWithPath("data[].title").type(JsonFieldType.STRING)
                        .description("뉴스 기사 제목"),
                    fieldWithPath("data[].thumbnailImg").type(JsonFieldType.STRING)
                        .description("뉴스 기사 썸네일")
                )
            ));
    }
}
