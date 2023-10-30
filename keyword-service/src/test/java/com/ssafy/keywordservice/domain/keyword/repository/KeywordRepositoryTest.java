package com.ssafy.keywordservice.domain.keyword.repository;

import com.ssafy.keywordservice.IntegrationTestSupport;
import com.ssafy.keywordservice.domain.keyword.Keyword;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class KeywordRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private KeywordRepository keywordRepository;

    @DisplayName("단어 존재 여부를 조회한다.")
    @Test
    void existByWord() {
        //given
        Keyword keyword = createdKeyword();

        //when
        Optional<Long> existWord = keywordRepository.existByWord("전인혁");

        //then
        assertThat(existWord).isPresent();
    }

    @DisplayName("단어로 조회한다.")
    @Test
    void findByWord() {
        //given
        Keyword keyword = createdKeyword();

        //when
        Optional<Keyword> findKeyword = keywordRepository.findByWord(keyword.getWord());

        //then
        assertThat(findKeyword).isPresent();
    }

    private Keyword createdKeyword() {
        Keyword keyword = Keyword.builder()
            .word("전인혁")
            .build();
        return keywordRepository.save(keyword);
    }
}