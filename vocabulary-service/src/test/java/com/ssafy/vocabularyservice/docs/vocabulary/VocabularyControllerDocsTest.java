package com.ssafy.vocabularyservice.docs.vocabulary;

import com.ssafy.vocabularyservice.api.controller.vocabulary.VocabularyController;
import com.ssafy.vocabularyservice.api.controller.vocabulary.request.CreateVocabularyRequest;
import com.ssafy.vocabularyservice.api.controller.vocabulary.response.CheckVocabularyResponse;
import com.ssafy.vocabularyservice.api.controller.vocabulary.response.VocabularyResponse;
import com.ssafy.vocabularyservice.api.controller.vocabulary.response.WordResponse;
import com.ssafy.vocabularyservice.api.service.vocabulary.VocabularyQueryService;
import com.ssafy.vocabularyservice.api.service.vocabulary.VocabularyService;
import com.ssafy.vocabularyservice.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VocabularyControllerDocsTest extends RestDocsSupport {

    private final VocabularyService vocabularyService = mock(VocabularyService.class);
    private final VocabularyQueryService vocabularyQueryService = mock(VocabularyQueryService.class);

    @Override
    protected Object initController() {
        return new VocabularyController(vocabularyService, vocabularyQueryService);
    }

    @DisplayName("단어장 등록 API")
    @Test
    void createVocabulary() throws Exception {
        CreateVocabularyRequest request = CreateVocabularyRequest.builder()
            .wordKey("92288")
            .build();

        WordResponse response = WordResponse.builder()
            .wordKey("92288")
            .content("돼지")
            .description("멧돼짓과의 포유류. 몸무게는 200~250kg이며, 다리와 꼬리가 짧고 주둥이가 삐죽하다.")
            .check(true)
            .build();

        given(vocabularyService.createVocabulary(anyString(), anyString()))
            .willReturn(response);

        mockMvc.perform(
                post("/vocabulary-service/api/{memberKey}", UUID.randomUUID().toString())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(document("create-vocabulary",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("wordKey").type(JsonFieldType.STRING)
                        .optional()
                        .description("단어 키")
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
                    fieldWithPath("data.wordKey").type(JsonFieldType.STRING)
                        .description("단어 키"),
                    fieldWithPath("data.content").type(JsonFieldType.STRING)
                        .description("단어 내용"),
                    fieldWithPath("data.description").type(JsonFieldType.STRING)
                        .description("단어 설명"),
                    fieldWithPath("data.check").type(JsonFieldType.BOOLEAN)
                        .description("단어 체크 여부")
                )
            ));
    }

    @DisplayName("나의 단어장 조회 API")
    @Test
    void getMyVocabulary() throws Exception {
        VocabularyResponse response1 = VocabularyResponse.builder()
            .vocabularyId(1L)
            .wordKey("99081")
            .content("돼지")
            .description("홍진식입니다.")
            .isChecked(true)
            .build();
        VocabularyResponse response2 = VocabularyResponse.builder()
            .vocabularyId(2L)
            .wordKey("99082")
            .content("치킨")
            .description("치킨은 BBQ입니다.")
            .isChecked(false)
            .build();

        List<VocabularyResponse> content = List.of(response1, response2);
        PageRequest pageRequest = PageRequest.of(0, 10);

        given(vocabularyQueryService.getMyVocabulary(anyString(), anyBoolean(), any(Pageable.class)))
            .willReturn(new PageImpl<>(content, pageRequest, 10));

        mockMvc.perform(
            get("/vocabulary-service/api/{memberKey}", UUID.randomUUID().toString())
                .param("check", "false")
                .param("pageNum", "1")
        )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-vocabulary",
                preprocessResponse(prettyPrint()),
                requestParameters(
                    parameterWithName("pageNum")
                        .description("페이지 번호"),
                    parameterWithName("check")
                        .description("체크된 단어 조회")
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
                    fieldWithPath("data.content").type(JsonFieldType.ARRAY)
                        .description("단어 데이터"),
                    fieldWithPath("data.content[].vocabularyId").type(JsonFieldType.NUMBER)
                        .description("단어장 PK"),
                    fieldWithPath("data.content[].wordKey").type(JsonFieldType.STRING)
                        .description("단어 키"),
                    fieldWithPath("data.content[].content").type(JsonFieldType.STRING)
                        .description("단어 내용"),
                    fieldWithPath("data.content[].description").type(JsonFieldType.STRING)
                        .description("단어 설명"),
                    fieldWithPath("data.content[].checked").type(JsonFieldType.BOOLEAN)
                        .description("단어 체크 여부"),
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

    @DisplayName("나의 단어장 체크된 단어 갯수 조회 API")
    @Test
    void getMyVocabularyWithCheck() throws Exception {
        CheckVocabularyResponse response = CheckVocabularyResponse.builder()
            .checkedCount(35)
            .totalCount(100)
            .build();

        given(vocabularyQueryService.getMyVocabularyWithCheck(anyString()))
            .willReturn(response);

        mockMvc.perform(
            get("/vocabulary-service/api/{memberKey}/check-count", UUID.randomUUID().toString())
        )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-vocabulary-check",
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
                    fieldWithPath("data.checkedCount").type(JsonFieldType.NUMBER)
                        .description("체크된 단어 수"),
                    fieldWithPath("data.totalCount").type(JsonFieldType.NUMBER)
                        .description("전체 단어 수")
                )
            ));
    }

    @DisplayName("단어장 체크 상태 변경 API")
    @Test
    void checkVocabulary() throws Exception {

        WordResponse response = WordResponse.builder()
            .wordKey("92288")
            .content("돼지")
            .description("멧돼짓과의 포유류. 몸무게는 200~250kg이며, 다리와 꼬리가 짧고 주둥이가 삐죽하다.")
            .check(true)
            .build();

        given(vocabularyService.checkVocabulary(anyLong()))
            .willReturn(response);

        mockMvc.perform(
                patch("/vocabulary-service/api/{vocabularyId}", 1L)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("check-vocabulary",
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
                    fieldWithPath("data.wordKey").type(JsonFieldType.STRING)
                        .description("단어 키"),
                    fieldWithPath("data.content").type(JsonFieldType.STRING)
                        .description("단어 내용"),
                    fieldWithPath("data.description").type(JsonFieldType.STRING)
                        .description("단어 설명"),
                    fieldWithPath("data.check").type(JsonFieldType.BOOLEAN)
                        .description("단어 체크 여부")
                )
            ));
    }

    @DisplayName("단어장 삭제 API")
    @Test
    void removeVocabulary() throws Exception {

        WordResponse response = WordResponse.builder()
            .wordKey("92288")
            .content("돼지")
            .description("멧돼짓과의 포유류. 몸무게는 200~250kg이며, 다리와 꼬리가 짧고 주둥이가 삐죽하다.")
            .check(true)
            .build();

        given(vocabularyService.removeVocabulary(anyLong()))
            .willReturn(response);

        mockMvc.perform(
                delete("/vocabulary-service/api/{vocabularyId}", 1L)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("remove-vocabulary",
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
                    fieldWithPath("data.wordKey").type(JsonFieldType.STRING)
                        .description("단어 키"),
                    fieldWithPath("data.content").type(JsonFieldType.STRING)
                        .description("단어 내용"),
                    fieldWithPath("data.description").type(JsonFieldType.STRING)
                        .description("단어 설명"),
                    fieldWithPath("data.check").type(JsonFieldType.BOOLEAN)
                        .description("단어 체크 여부")
                )
            ));
    }
}
