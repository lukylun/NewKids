package com.ssafy.keywordbatch.domain.article.repository;

import com.ssafy.keywordbatch.domain.article.ArticleLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleLogRepository extends JpaRepository<ArticleLog, Long> {
}
