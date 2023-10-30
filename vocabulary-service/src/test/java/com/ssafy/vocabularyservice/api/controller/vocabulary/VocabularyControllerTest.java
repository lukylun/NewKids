package com.ssafy.vocabularyservice.api.controller.vocabulary;

import com.ssafy.vocabularyservice.ControllerTestSupport;
import com.ssafy.vocabularyservice.api.controller.vocabulary.request.CreateVocabularyRequest;
import com.ssafy.vocabularyservice.api.controller.vocabulary.response.WordResponse;
import com.ssafy.vocabularyservice.api.service.vocabulary.VocabularyQueryService;
import com.ssafy.vocabularyservice.api.service.vocabulary.VocabularyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {VocabularyController.class})
class VocabularyControllerTest extends ControllerTestSupport {

    @MockBean
    private VocabularyService vocabularyService;

    @MockBean
    private VocabularyQueryService vocabularyQueryService;

    @DisplayName("새로운 단어를 단어장에 등록할 때 단어키는 필수값이다.")
    @Test
    void createVocabularyWithoutWordKey() throws Exception {
        //given
        CreateVocabularyRequest request = CreateVocabularyRequest.builder()
            .build();

        //when //then
        mockMvc.perform(
                post("/vocabulary-service/api/{memberKey}", UUID.randomUUID().toString())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("단어 키는 필수입니다."))
            .andExpect(jsonPath("$.data").isEmpty());
    }

    @DisplayName("새로운 단어를 단어장에 등록한다.")
    @Test
    void createVocabulary() throws Exception {
        //given
        CreateVocabularyRequest request = CreateVocabularyRequest.builder()
            .wordKey("92288")
            .build();

        WordResponse response = WordResponse.builder()
            .wordKey("92288")
            .content("돼지")
            .description("멧돼짓과의 포유류. 몸무게는 200~250kg이며, 다리와 꼬리가 짧고 주둥이가 삐죽하다.")
            .build();

        given(vocabularyService.createVocabulary(anyString(), anyString()))
            .willReturn(response);

        //when //then
        mockMvc.perform(
                post("/vocabulary-service/api/{memberKey}", UUID.randomUUID().toString())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.code").value("201"))
            .andExpect(jsonPath("$.status").value("CREATED"))
            .andExpect(jsonPath("$.message").value("CREATED"))
            .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @DisplayName("단어장의 체크 상태를 변경한다.")
    @Test
    void checkVocabulary() throws Exception {
        //given
        WordResponse response = WordResponse.builder()
            .wordKey("92288")
            .content("돼지")
            .description("멧돼짓과의 포유류. 몸무게는 200~250kg이며, 다리와 꼬리가 짧고 주둥이가 삐죽하다.")
            .check(true)
            .build();

        given(vocabularyService.checkVocabulary(anyLong()))
            .willReturn(response);

        //when //then
        mockMvc.perform(
                patch("/vocabulary-service/api/{vocabularyId}", 1L)
            )
            .andDo(print())
            .andExpect(status().isFound())
            .andExpect(jsonPath("$.code").value("302"))
            .andExpect(jsonPath("$.status").value("FOUND"))
            .andExpect(jsonPath("$.message").value("FOUND"))
            .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @DisplayName("단어장에 등록된 단어를 삭제한다")
    @Test
    void removeVocabulary() throws Exception {
        //given
        WordResponse response = WordResponse.builder()
            .wordKey("92288")
            .content("돼지")
            .description("멧돼짓과의 포유류. 몸무게는 200~250kg이며, 다리와 꼬리가 짧고 주둥이가 삐죽하다.")
            .check(true)
            .build();

        given(vocabularyService.removeVocabulary(anyLong()))
            .willReturn(response);

        //when //then
        mockMvc.perform(
            delete("/vocabulary-service/api/{vocabularyId}", 1L)
        )
            .andDo(print())
            .andExpect(status().isFound())
            .andExpect(jsonPath("$.code").value("302"))
            .andExpect(jsonPath("$.status").value("FOUND"))
            .andExpect(jsonPath("$.message").value("FOUND"))
            .andExpect(jsonPath("$.data").isNotEmpty());
    }
}