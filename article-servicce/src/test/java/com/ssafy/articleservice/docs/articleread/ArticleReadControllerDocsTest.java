package com.ssafy.articleservice.docs.articleread;

import com.ssafy.articleservice.api.controller.article.ArticleReadController;
import com.ssafy.articleservice.api.controller.article.request.CreateArticleReadRequest;
import com.ssafy.articleservice.api.controller.article.response.ArticleReadResponse;
import com.ssafy.articleservice.api.service.articleread.ArticleReadQueryService;
import com.ssafy.articleservice.api.service.articleread.ArticleReadService;
import com.ssafy.articleservice.docs.RestDocsSupport;
import com.ssafy.articleservice.messagequeue.KafkaProducer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ArticleReadControllerDocsTest extends RestDocsSupport {

    private final ArticleReadService articleReadService = mock(ArticleReadService.class);
    private final ArticleReadQueryService articleReadQueryService = mock(ArticleReadQueryService.class);
    private final KafkaProducer kafkaProducer = mock(KafkaProducer.class);

    @Override
    protected Object initController() {
        return new ArticleReadController(articleReadService, articleReadQueryService, kafkaProducer);
    }

    @DisplayName("읽은 뉴스 기사 목록 등록 API")
    @Test
    void createArticleRead() throws Exception {
        CreateArticleReadRequest request = CreateArticleReadRequest.builder()
            .articleId(31245L)
            .build();

        ArticleReadResponse response = ArticleReadResponse.builder()
            .articleId(35231L)
            .title("홍진식 직캠 역주행으로 빌보드 입성")
            .thumbnailImg("http://응가응가.com")
            .build();

        given(articleReadService.createArticleRead(anyString(), anyLong()))
            .willReturn(response);

        mockMvc.perform(
                post("/article-service/api/read/{memberKey}", UUID.randomUUID().toString())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", "accessToken")
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(document("create-read-article",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("articleId").type(JsonFieldType.NUMBER)
                        .optional()
                        .description("뉴스 기사 PK")
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
                    fieldWithPath("data.articleId").type(JsonFieldType.NUMBER)
                        .description("뉴스 기사 PK"),
                    fieldWithPath("data.title").type(JsonFieldType.STRING)
                        .description("뉴스 기사 제목"),
                    fieldWithPath("data.thumbnailImg").type(JsonFieldType.STRING)
                        .description("뉴스 기사 썸네일")
                )
            ));
    }

    @DisplayName("읽은 뉴스 기사 목록 조회 API")
    @Test
    void getArticleRead() throws Exception {
        ArticleReadResponse response1 = ArticleReadResponse.builder()
            .articleId(35231L)
            .title("홍진식 직캠 역주행으로 빌보드 입성")
            .thumbnailImg("http://응가응가.com")
            .build();

        ArticleReadResponse response2 = ArticleReadResponse.builder()
            .articleId(35232L)
            .title("홍진식 돼지 꿀꿀")
            .thumbnailImg("http://응가응가.com")
            .build();

        List<ArticleReadResponse> responses = List.of(response1, response2);

        PageRequest pageRequest = PageRequest.of(0, 8);

        given(articleReadQueryService.getMyArticleRead(anyString(), any(Pageable.class)))
            .willReturn(new PageImpl<>(responses, pageRequest, 20));

        mockMvc.perform(
                get("/article-service/api/read/{memberKey}", UUID.randomUUID().toString())
                    .param("pageNum", "1")
                    .header("Authorization", "accessToken")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-read-article",
                preprocessResponse(prettyPrint()),
                requestParameters(
                    parameterWithName("pageNum")
                        .description("페이지 번호")
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
                    fieldWithPath("data.content[].thumbnailImg").type(JsonFieldType.STRING)
                        .description("기사 썸네일"),
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
}
