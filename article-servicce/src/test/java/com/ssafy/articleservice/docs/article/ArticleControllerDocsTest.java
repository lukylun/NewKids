package com.ssafy.articleservice.docs.article;

import com.ssafy.articleservice.api.controller.article.ArticleController;
import com.ssafy.articleservice.api.controller.article.response.ArticleDetailResponse;
import com.ssafy.articleservice.api.controller.article.response.ArticleResponse;
import com.ssafy.articleservice.api.service.article.ArticleQueryService;
import com.ssafy.articleservice.api.service.article.ArticleService;
import com.ssafy.articleservice.docs.RestDocsSupport;
import com.ssafy.articleservice.domain.article.repository.dto.ArticleSearchCond;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ArticleControllerDocsTest extends RestDocsSupport {

    private final ArticleService articleService = mock(ArticleService.class);
    private final ArticleQueryService articleQueryService = mock(ArticleQueryService.class);

    @Override
    protected Object initController() {
        return new ArticleController(articleService, articleQueryService);
    }

    @DisplayName("뉴스 기사 조회 API")
    @Test
    void getArticles() throws Exception {

        ArticleResponse response  = ArticleResponse.builder()
            .articleId(1L)
            .title("오늘 점심을 뭐먹을까...")
            .subTitle("먹고싶지 않다")
            .writer("임우택")
            .content("배가 너무너무너무 고파요")
            .publishedDate(LocalDateTime.now())
            .thumbnailImg("http://lunch.png")
            .build();

        List<ArticleResponse> content = List.of(response);

        PageRequest pageRequest = PageRequest.of(1, 10);

        given(articleQueryService.getArticles(any(ArticleSearchCond.class), any(Pageable.class)))
            .willReturn(new PageImpl<>(content, pageRequest, 11));


        mockMvc.perform(
                get("/article-service/api")
                    .param("content", "돼지")
                    .param("pageNum", "1")
                    .param("startDate", "2023-09-10")
                    .param("endDate", "2023-09-19")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-article",
                preprocessResponse(prettyPrint()),
                requestParameters(
                    parameterWithName("content")
                        .description("조회할 제목 or 내용"),
                    parameterWithName("pageNum")
                        .description("페이지 번호"),
                    parameterWithName("startDate")
                        .optional()
                        .description("시작일"),
                    parameterWithName("endDate")
                        .optional()
                        .description("종료일")
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
                    fieldWithPath("data.content").type(JsonFieldType.ARRAY)
                        .description("기사 내역 데이터"),
                    fieldWithPath("data.content[].articleId").type(JsonFieldType.NUMBER)
                        .description("기사 PK"),
                    fieldWithPath("data.content[].title").type(JsonFieldType.STRING)
                        .description("기사 제목"),
                    fieldWithPath("data.content[].subTitle").type(JsonFieldType.STRING)
                        .description("기사 부제목"),
                    fieldWithPath("data.content[].writer").type(JsonFieldType.STRING)
                        .description("기사 작성자"),
                    fieldWithPath("data.content[].content").type(JsonFieldType.STRING)
                        .description("기사 내용 (100자 제한)"),
                    fieldWithPath("data.content[].publishedDate").type(JsonFieldType.ARRAY)
                        .description("기사 작성일"),
                    fieldWithPath("data.content[].thumbnailImg").type(JsonFieldType.STRING)
                        .description("기사 썸네일 이미지"),
                    fieldWithPath("data.pageable").type(JsonFieldType.OBJECT)
                        .description("응답 데이터"),
                    fieldWithPath("data.pageable.sort").type(JsonFieldType.OBJECT)
                        .description("응답 데이터"),
                    fieldWithPath("data.pageable.sort.empty").type(JsonFieldType.BOOLEAN)
                        .description("응답 데이터"),
                    fieldWithPath("data.pageable.sort.sorted").type(JsonFieldType.BOOLEAN)
                        .description("응답 데이터"),
                    fieldWithPath("data.pageable.sort.unsorted").type(JsonFieldType.BOOLEAN)
                        .description("응답 데이터"),
                    fieldWithPath("data.pageable.offset").type(JsonFieldType.NUMBER)
                        .description("응답 데이터"),
                    fieldWithPath("data.pageable.pageNumber").type(JsonFieldType.NUMBER)
                        .description("응답 데이터"),
                    fieldWithPath("data.pageable.pageSize").type(JsonFieldType.NUMBER)
                        .description("응답 데이터"),
                    fieldWithPath("data.pageable.paged").type(JsonFieldType.BOOLEAN)
                        .description("응답 데이터"),
                    fieldWithPath("data.pageable.unpaged").type(JsonFieldType.BOOLEAN)
                        .description("응답 데이터"),
                    fieldWithPath("data.totalPages").type(JsonFieldType.NUMBER)
                        .description("총 페이지 수"),
                    fieldWithPath("data.totalElements").type(JsonFieldType.NUMBER)
                        .description("DB의 전체 데이터 갯수"),
                    fieldWithPath("data.last").type(JsonFieldType.BOOLEAN)
                        .description("마지막 페이지라면 true"),
                    fieldWithPath("data.size").type(JsonFieldType.NUMBER)
                        .description("페이지 당 나타낼 수 있는 데이터의 갯수"),
                    fieldWithPath("data.sort").type(JsonFieldType.OBJECT)
                        .description("응답 데이터"),
                    fieldWithPath("data.sort.empty").type(JsonFieldType.BOOLEAN)
                        .description("응답 데이터"),
                    fieldWithPath("data.sort.sorted").type(JsonFieldType.BOOLEAN)
                        .description("응답 데이터"),
                    fieldWithPath("data.sort.unsorted").type(JsonFieldType.BOOLEAN)
                        .description("응답 데이터"),
                    fieldWithPath("data.number").type(JsonFieldType.NUMBER)
                        .description("현재 페이지 번호"),
                    fieldWithPath("data.numberOfElements").type(JsonFieldType.NUMBER)
                        .description("실제 데이터의 갯수"),
                    fieldWithPath("data.first").type(JsonFieldType.BOOLEAN)
                        .description("첫번째 페이지이면 true"),
                    fieldWithPath("data.empty").type(JsonFieldType.BOOLEAN)
                        .description("리스트가 비어있는지 여부")
                )
            ));
    }

    @DisplayName("뉴스 기사 단건 조회 API")
    @Test
    void getArticle() throws Exception {
        ArticleDetailResponse response = ArticleDetailResponse.builder()
            .articleId(1L)
            .title("")
            .subTitle("")
            .writer("")
            .publishedDate(LocalDateTime.now())
            .content("")
            .thumbnailImg("")
            .build();

        given(articleQueryService.getArticle(anyLong()))
            .willReturn(response);

        mockMvc.perform(
                get("/article-service/api/{articleId}", 1L)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-detail-article",
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER)
                        .description("코드"),
                    fieldWithPath("status").type(JsonFieldType.STRING)
                        .description("상태"),
                    fieldWithPath("message").type(JsonFieldType.STRING)
                        .description("메시지"),
                    fieldWithPath("data").type(JsonFieldType.OBJECT)
                        .description("응답데이터"),
                    fieldWithPath("data.articleId").type(JsonFieldType.NUMBER)
                        .description("기사 PK"),
                    fieldWithPath("data.title").type(JsonFieldType.STRING)
                        .description("기사 제목"),
                    fieldWithPath("data.subTitle").type(JsonFieldType.STRING)
                        .description("기사 부제목"),
                    fieldWithPath("data.writer").type(JsonFieldType.STRING)
                        .description("기사 작성자"),
                    fieldWithPath("data.publishedDate").type(JsonFieldType.ARRAY)
                        .description("기사 작성일"),
                    fieldWithPath("data.content").type(JsonFieldType.STRING)
                        .description("기사 내용"),
                    fieldWithPath("data.thumbnailImg").type(JsonFieldType.STRING)
                        .description("기사 썸네일 이미지")
                )
            ));
    }

    @DisplayName("뉴스 기사 삭제 API")
    @Test
    void removeArticle() throws Exception {

    }
}
