package com.ssafy.quizservice.domain.weekly.repository;

import com.ssafy.quizservice.domain.weekly.Weekly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyRedisRepository extends JpaRepository<Weekly, String> {
}
