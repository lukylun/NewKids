package com.ssafy.quizservice.api.service.quiz;

import com.ssafy.quizservice.api.controller.quiz.response.QuizResultResponse;
import com.ssafy.quizservice.api.controller.quiz.response.QuizWordResponse;
import com.ssafy.quizservice.api.service.quiz.exception.NotEnoughDataException;
import com.ssafy.quizservice.client.VocabularyServiceClient;
import com.ssafy.quizservice.client.response.WordClientResponse;
import com.ssafy.quizservice.domain.quiz.Problem;
import com.ssafy.quizservice.domain.quiz.Quiz;
import com.ssafy.quizservice.domain.quiz.repository.QuizRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuizService {

    private final QuizRedisRepository quizRedisRepository;
    private final VocabularyServiceClient vocabularyServiceClient;

    public String getMyVocabulary(String memberKey) {
        List<WordClientResponse> responses = vocabularyServiceClient.getMyVocabulary(memberKey);

        if (responses.size() < 10) {
            throw new NotEnoughDataException("단어장에 저장된 단어의 갯수가 부족합니다.");
        }

        List<Problem> problems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            WordClientResponse response = responses.get(i);
            Problem problem = Problem.builder()
                .no(i + 1)
                .word(response.getContent())
                .description(response.getDescription())
                .build();
            problems.add(problem);
        }

        Quiz quiz = Quiz.builder()
            .memberKey(memberKey)
            .problems(problems)
            .build();

        Quiz savedQuiz = quizRedisRepository.save(quiz);

        return savedQuiz.getMemberKey();
    }

    public QuizWordResponse getNextWord(String memberKey) {
        Optional<Quiz> findQuiz = quizRedisRepository.findById(memberKey);
        if (findQuiz.isEmpty()) {
            throw new NoSuchElementException("미등록");
        }
        Quiz quiz = findQuiz.get();

        List<Problem> problems = quiz.getProblems();
        Problem problem = problems.get(0);

        return QuizWordResponse.of(problem);
    }

    public boolean checkAnswer(String memberKey, String answer) {
        Optional<Quiz> findQuiz = quizRedisRepository.findById(memberKey);
        if (findQuiz.isEmpty()) {
            throw new NoSuchElementException("미등록");
        }
        Quiz quiz = findQuiz.get();

        List<Problem> problems = quiz.getProblems();
        Problem problem = problems.get(0);

        boolean result = problem.getWord().equals(answer);

        quiz.removeProblem(result);

        quizRedisRepository.save(quiz);
        return result;
    }

    public QuizResultResponse resultQuiz(String memberKey) {
        Optional<Quiz> findQuiz = quizRedisRepository.findById(memberKey);
        if (findQuiz.isEmpty()) {
            throw new NoSuchElementException("미등록");
        }
        Quiz quiz = findQuiz.get();

        return QuizResultResponse.of(quiz.getRightAnswer());
    }
}
