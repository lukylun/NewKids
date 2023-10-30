package com.ssafy.quizservice.api.service.weekly;

import com.ssafy.quizservice.api.controller.weekly.response.WeeklyQuizResultResponse;
import com.ssafy.quizservice.api.controller.weekly.response.WeeklyQuizWordResponse;
import com.ssafy.quizservice.client.KeywordServiceClient;
import com.ssafy.quizservice.client.response.KeywordQuizClientResponse;
import com.ssafy.quizservice.domain.weekly.Problem;
import com.ssafy.quizservice.domain.weekly.Weekly;
import com.ssafy.quizservice.domain.weekly.repository.WeeklyRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class WeeklyService {

    private final WeeklyRedisRepository weeklyRedisRepository;
    private final KeywordServiceClient keywordServiceClient;

    public String startWeeklyQuiz(String memberKey) {
        List<KeywordQuizClientResponse> responses = keywordServiceClient.getWeeklyQuizKeywords();

        List<Problem> problems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            KeywordQuizClientResponse response = responses.get(i);
            Problem problem = Problem.builder()
                .no(i + 1)
                .answerWord(response.getAnswer())
                .description(response.getDescription())
                .contents(response.getContents())
                .build();
            problems.add(problem);
        }

        Weekly weekly = Weekly.builder()
            .memberKey(memberKey)
            .rightAnswer(0)
            .problems(problems)
            .build();

        Weekly savedWeekly = weeklyRedisRepository.save(weekly);

        return savedWeekly.getMemberKey();
    }

    public WeeklyQuizWordResponse getNextWeeklyWord(String memberKey) {
        Optional<Weekly> findWeekly = weeklyRedisRepository.findById(memberKey);
        if (findWeekly.isEmpty()) {
            throw new NoSuchElementException("미등록");
        }
        Weekly weekly = findWeekly.get();

        List<Problem> problems = weekly.getProblems();
        Problem problem = problems.get(0);

        return WeeklyQuizWordResponse.of(problem);
    }

    public boolean checkWeeklyAnswer(String memberKey, String answer) {
        Optional<Weekly> findWeekly = weeklyRedisRepository.findById(memberKey);
        if (findWeekly.isEmpty()) {
            throw new NoSuchElementException("미등록");
        }
        Weekly weekly = findWeekly.get();

        List<Problem> problems = weekly.getProblems();
        Problem problem = problems.get(0);

        boolean result = problem.getAnswerWord().equals(answer);

        weekly.removeProblem(result);

        weeklyRedisRepository.save(weekly);
        return result;
    }

    public WeeklyQuizResultResponse resultWeeklyQuiz(String memberKey) {
        Optional<Weekly> findWeekly = weeklyRedisRepository.findById(memberKey);
        if (findWeekly.isEmpty()) {
            throw new NoSuchElementException("미등록");
        }
        Weekly weekly = findWeekly.get();

        return WeeklyQuizResultResponse.of(weekly.getRightAnswer());
    }
}
