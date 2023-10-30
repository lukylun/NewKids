package com.ssafy.openapiservice.docs.openapi;

import com.ssafy.openapiservice.api.controller.openapi.OpenApiController;
import com.ssafy.openapiservice.api.controller.openapi.response.WordResponse;
import com.ssafy.openapiservice.api.service.openapi.OpenApiService;
import com.ssafy.openapiservice.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;

import static org.mockito.BDDMockito.anyString;
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

public class OpenApiControllerDocsTest extends RestDocsSupport {

    private final OpenApiService openApiService = mock(OpenApiService.class);

    @Override
    protected Object initController() {
        return new OpenApiController(openApiService);
    }

    @DisplayName("Open API 단어 조회 API")
    @Test
    void searchDictionary() throws Exception {
        WordResponse response = WordResponse.builder()
            .wordKey("53456")
            .content("홍진식")
            .description("꿀꿀이")
            .build();

        given(openApiService.searchDictionary(anyString()))
            .willReturn(List.of(response));

        mockMvc.perform(
            get("/openapi-service/api/search")
                .param("query", "나무")
        )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-openapi",
                preprocessResponse(prettyPrint()),
                requestParameters(
                    parameterWithName("query")
                        .optional()
                        .description("조회할 단어")
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
                    fieldWithPath("data[].wordKey").type(JsonFieldType.STRING)
                        .description("단어 키"),
                    fieldWithPath("data[].content").type(JsonFieldType.STRING)
                        .description("단어"),
                    fieldWithPath("data[].description").type(JsonFieldType.STRING)
                        .description("단어 설명")
                )
            ));
    }
}
