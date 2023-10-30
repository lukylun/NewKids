package com.ssafy.quizservice.api.controller.weekly;

import com.ssafy.quizservice.api.controller.ApiResponse;
import com.ssafy.quizservice.api.controller.weekly.request.CheckAnswerRequest;
import com.ssafy.quizservice.api.controller.weekly.response.WeeklyQuizResultResponse;
import com.ssafy.quizservice.api.controller.weekly.response.WeeklyQuizWordResponse;
import com.ssafy.quizservice.api.service.weekly.WeeklyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/quiz-service/api/{memberKey}/weekly")
public class WeeklyController {

    private final WeeklyService weeklyService;

    /**
     * 주간 키워드 퀴즈 시작 등록 API
     *
     * @param memberKey 회원 고유키
     * @return 시작한 회원 고유키
     */
    @PostMapping("/start")
    public ApiResponse<String> loadingWeeklyQuiz(@PathVariable String memberKey) {
        log.debug("call WeeklyController#loadingWeeklyQuiz");
        log.debug("memberKey={}", memberKey);

        String key = weeklyService.startWeeklyQuiz(memberKey);
        log.debug("key={}", key);

        return ApiResponse.ok(key);
    }

    /**
     * 주간 키워드 퀴즈 다음 단어 호출 API
     *
     * @param memberKey 회원 고유키
     * @return 다음 키워드 퀴즈 단어 정보
     */
    @PostMapping("/next")
    public ApiResponse<WeeklyQuizWordResponse> nextWeeklyWord(@PathVariable String memberKey) {
        log.debug("call WeeklyController#nextWeeklyWord");
        log.debug("memberKey={}", memberKey);

        WeeklyQuizWordResponse response = weeklyService.getNextWeeklyWord(memberKey);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }

    /**
     * 주간 키워드 퀴즈 정답 체크 API
     *
     * @param request 퀴즈 정답
     * @param memberKey 회원 고유키
     * @return 결과
     */
    @PostMapping("/answer")
    public ApiResponse<Boolean> checkWeeklyAnswer(@Valid @RequestBody CheckAnswerRequest request, @PathVariable String memberKey) {
        log.debug("call WeeklyController#checkWeeklyAnswer");
        log.debug("memberKey={}", memberKey);

        boolean result = weeklyService.checkWeeklyAnswer(memberKey, request.getAnswer());
        log.debug("result={}", result);

        return ApiResponse.ok(result);
    }

    /**
     * 주간 키워드 퀴즈 결과 API
     *
     * @param memberKey 회원 고유키
     * @return 주간 키워드 퀴즈 결과 정보
     */
    @PostMapping("/result")
    public ApiResponse<WeeklyQuizResultResponse> resultWeeklyQuiz(@PathVariable String memberKey) {
        log.debug("call WeeklyController#resultWeeklyQuiz");
        log.debug("memberKey={}", memberKey);

        WeeklyQuizResultResponse response = weeklyService.resultWeeklyQuiz(memberKey);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }
}
