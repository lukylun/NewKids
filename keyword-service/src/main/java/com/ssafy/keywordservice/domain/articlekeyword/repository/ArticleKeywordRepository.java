package com.ssafy.keywordservice.domain.articlekeyword.repository;

import com.ssafy.keywordservice.domain.articlekeyword.ArticleKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleKeywordRepository extends JpaRepository<ArticleKeyword, Long> {
}
