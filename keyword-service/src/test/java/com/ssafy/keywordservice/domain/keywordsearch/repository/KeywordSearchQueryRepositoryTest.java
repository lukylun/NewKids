package com.ssafy.keywordservice.domain.keywordsearch.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KeywordSearchQueryRepositoryTest {

    @Autowired
    private KeywordSearchQueryRepository keywordSearchQueryRepository;

    @Test
    void test() {
        keywordSearchQueryRepository.findTop10();

    }
}