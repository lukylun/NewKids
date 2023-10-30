package com.ssafy.quizservice.api.controller.quiz;

import com.ssafy.quizservice.api.controller.ApiResponse;
import com.ssafy.quizservice.api.controller.quiz.request.CheckAnswerRequest;
import com.ssafy.quizservice.api.controller.quiz.response.QuizResultResponse;
import com.ssafy.quizservice.api.controller.quiz.response.QuizWordResponse;
import com.ssafy.quizservice.api.service.quiz.QuizService;
import com.ssafy.quizservice.messagequeue.KafkaProducer;
import com.ssafy.quizservice.messagequeue.dto.MemberExpDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 퀴즈 API
 * @author 임우택
 */
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/quiz-service/api/{memberKey}")
public class QuizController {

    private final QuizService quizService;
    private final KafkaProducer kafkaProducer;

    /**
     * 퀴즈 시작 등록 API
     *
     * @param memberKey 회원 고유키
     * @return 회원 고유키
     */
    @PostMapping("/start")
    public ApiResponse<String> loadingQuiz(@PathVariable String memberKey) {
        log.debug("call QuizController#loadingQuiz");
        log.debug("memberKey={}", memberKey);

        String key = quizService.getMyVocabulary(memberKey);
        log.debug("key={}", key);

        return ApiResponse.ok(key);
    }

    /**
     * 퀴즈 다음 단어 호출 API
     *
     * @param memberKey 회원 고유키
     * @return 퀴즈 다음 단어 정보
     */
    @PostMapping("/next")
    public ApiResponse<QuizWordResponse> nextWord(@PathVariable String memberKey) {
        log.debug("call QuizController#nextWord");
        log.debug("memberKey={}", memberKey);

        QuizWordResponse response = quizService.getNextWord(memberKey);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }

    /**
     * 퀴즈 정답 체크 API
     *
     *  @param request 정답 체크 단어
     * @param memberKey 회원 고유키
     * @return 정답 여부
     */
    @PostMapping("/answer")
    public ApiResponse<Boolean> checkAnswer(@Valid @RequestBody CheckAnswerRequest request, @PathVariable String memberKey) {
        log.debug("call QuizController#checkAnswer");
        log.debug("memberKey={}", memberKey);

        boolean result = quizService.checkAnswer(memberKey, request.getAnswer());
        log.debug("result={}", result);

        return ApiResponse.ok(result);
    }

    /**
     * 퀴즈 결과 API
     *
     * @param memberKey 회원 고유키
     * @return 퀴즈 결과 정보
     */
    @PostMapping("/result")
    public ApiResponse<QuizResultResponse> resultQuiz(@PathVariable String memberKey) {
        log.debug("call QuizController#resultQuiz");
        log.debug("memberKey={}", memberKey);

        QuizResultResponse response = quizService.resultQuiz(memberKey);
        log.debug("response={}", response);

        MemberExpDto memberExpDto = MemberExpDto.builder()
            .memberKey(memberKey)
            .exp(response.getTotalScore())
            .build();

        kafkaProducer.send("exp-member-topic", memberExpDto);

        return ApiResponse.ok(response);
    }
}
