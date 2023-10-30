package com.ssafy.quizservice.domain.quiz.repository;

import com.ssafy.quizservice.domain.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRedisRepository extends JpaRepository<Quiz, String> {
}
