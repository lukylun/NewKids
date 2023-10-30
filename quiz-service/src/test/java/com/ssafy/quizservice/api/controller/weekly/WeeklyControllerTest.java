package com.ssafy.quizservice.api.controller.weekly;

import com.ssafy.quizservice.ControllerTestSupport;
import com.ssafy.quizservice.api.controller.weekly.response.WeeklyQuizWordResponse;
import com.ssafy.quizservice.api.service.weekly.WeeklyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {WeeklyController.class})
class WeeklyControllerTest extends ControllerTestSupport {

    @MockBean
    private WeeklyService weeklyService;

    @DisplayName("주간 키워드 퀴즈를 시작한다.")
    @Test
    void loadingWeeklyQuiz() throws Exception {
        //given
        String memberKey = UUID.randomUUID().toString();
        given(weeklyService.startWeeklyQuiz(anyString()))
            .willReturn(memberKey);

        //when //then
        mockMvc.perform(
                post("/quiz-service/api/{memberKey}/weekly/start", memberKey)
                    .header("Authorization", "Access Token")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("200"))
            .andExpect(jsonPath("$.status").value("OK"))
            .andExpect(jsonPath("$.message").value("SUCCESS"))
            .andExpect(jsonPath("$.data").isString());
    }

    @DisplayName("주간 키워드 퀴즈 다음 단어를 호출한다.")
    @Test
    void nextWeeklyWord() throws Exception {
        //given
        String memberKey = UUID.randomUUID().toString();

        WeeklyQuizWordResponse response = WeeklyQuizWordResponse.builder()
            .no(1)
            .answerWord("홍진식")
            .description("광주 C205 대표 돼지")
            .contents(List.of("임우택", "최영환", "홍진식", "전인혁"))
            .build();

        given(weeklyService.getNextWeeklyWord(anyString()))
            .willReturn(response);

        //when //then
        mockMvc.perform(
                post("/quiz-service/api/{memberKey}/weekly/next", memberKey)
                    .header("Authorization", "Access Token")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("200"))
            .andExpect(jsonPath("$.status").value("OK"))
            .andExpect(jsonPath("$.message").value("SUCCESS"))
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.data.no").isNumber())
            .andExpect(jsonPath("$.data.answerWord").isString())
            .andExpect(jsonPath("$.data.description").isString())
            .andExpect(jsonPath("$.data.contents").isArray());
    }


}