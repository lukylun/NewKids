package com.ssafy.keywordservice.domain.keywordsearch.repository;

import com.ssafy.keywordservice.domain.keywordsearch.KeywordSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordSearchRepository extends JpaRepository<KeywordSearch, Long> {
}
