package com.ssafy.quizservice.api.controller.quiz;

import com.ssafy.quizservice.ControllerTestSupport;
import com.ssafy.quizservice.api.controller.quiz.request.CheckAnswerRequest;
import com.ssafy.quizservice.api.controller.quiz.response.QuizResultResponse;
import com.ssafy.quizservice.api.controller.quiz.response.QuizWordResponse;
import com.ssafy.quizservice.api.service.quiz.QuizService;
import com.ssafy.quizservice.messagequeue.KafkaProducer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {QuizController.class})
class QuizControllerTest extends ControllerTestSupport {

    @MockBean
    private QuizService quizService;

    @MockBean
    private KafkaProducer kafkaProducer;

    @DisplayName("퀴즈 다음 단어를 호출한다.")
    @Test
    void loadingQuiz() throws Exception {
        //given
        QuizWordResponse response = QuizWordResponse.builder()
            .no(1)
            .word("홍진식")
            .description("광주 C205 대표 돼지")
            .build();

        given(quizService.getNextWord(anyString()))
            .willReturn(response);

        //when //then
        mockMvc.perform(
                post("/quiz-service/api/{memberKey}/next", UUID.randomUUID().toString())
                    .header("Authorization", "Access Token")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("200"))
            .andExpect(jsonPath("$.status").value("OK"))
            .andExpect(jsonPath("$.message").value("SUCCESS"))
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.data.no").isNumber())
            .andExpect(jsonPath("$.data.word").isString())
            .andExpect(jsonPath("$.data.description").isString());
    }

    @DisplayName("퀴즈 정답을 체크 요청은 필수이다.")
    @Test
    void checkAnswerWithoutAnswer() throws Exception {
        //given
        CheckAnswerRequest request = CheckAnswerRequest.builder()
            .build();

        //when //then
        mockMvc.perform(
                post("/quiz-service/api/{memberKey}/answer", UUID.randomUUID().toString())
                    .header("Authorization", "Access Token")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("정답 입력은 필수입니다."))
            .andExpect(jsonPath("$.data").isEmpty());
    }

    @DisplayName("퀴즈 정답을 체크한다.")
    @Test
    void checkAnswer() throws Exception {
        //given
        CheckAnswerRequest request = CheckAnswerRequest.builder()
            .answer("홍진식")
            .build();

        given(quizService.checkAnswer(anyString(), anyString()))
            .willReturn(true);

        //when //then
        mockMvc.perform(
                post("/quiz-service/api/{memberKey}/answer", UUID.randomUUID().toString())
                    .header("Authorization", "Access Token")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("200"))
            .andExpect(jsonPath("$.status").value("OK"))
            .andExpect(jsonPath("$.message").value("SUCCESS"))
            .andExpect(jsonPath("$.data").isBoolean());
    }

    @DisplayName("퀴즈 결과를 호출한다.")
    @Test
    void resultQuiz() throws Exception {
        //given
        QuizResultResponse response = QuizResultResponse.builder()
            .totalScore(70)
            .rightQuizCount(7)
            .build();

        given(quizService.resultQuiz(anyString()))
            .willReturn(response);

        //when //then
        mockMvc.perform(
                post("/quiz-service/api/{memberKey}/result", UUID.randomUUID().toString())
                    .header("Authorization", "Access Token")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("200"))
            .andExpect(jsonPath("$.status").value("OK"))
            .andExpect(jsonPath("$.message").value("SUCCESS"))
            .andExpect(jsonPath("$.data").isNotEmpty())
            .andExpect(jsonPath("$.data.totalScore").isNumber())
            .andExpect(jsonPath("$.data.rightQuizCount").isNumber());
    }
}