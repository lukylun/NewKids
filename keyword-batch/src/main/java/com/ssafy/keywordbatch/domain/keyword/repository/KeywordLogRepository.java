package com.ssafy.keywordbatch.domain.keyword.repository;

import com.ssafy.keywordbatch.domain.keyword.KeywordLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordLogRepository extends JpaRepository<KeywordLog, Long> {
}
