package com.ssafy.keywordservice.api.service.keyword;

import com.ssafy.keywordservice.IntegrationTestSupport;
import com.ssafy.keywordservice.api.controller.keyword.response.KeywordResponse;
import com.ssafy.keywordservice.api.service.keyword.exception.DuplicateException;
import com.ssafy.keywordservice.domain.keyword.Keyword;
import com.ssafy.keywordservice.domain.keyword.repository.KeywordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KeywordServiceTest extends IntegrationTestSupport {

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private KeywordRepository keywordRepository;

    @DisplayName("이미 등록된 단어를 키워드에 등록하면 예외가 발생한다.")
    @Test
    void createKeywordWithDuplication() {
        //given
        Keyword keyword = createdKeyword();

        //when //then
        assertThatThrownBy(() -> keywordService.createKeyword("전인혁"))
            .isInstanceOf(DuplicateException.class)
            .hasMessage("이미 등록된 키워드 입니다.");
    }

    @DisplayName("단어를 입력받아 키워드를 등록한다.")
    @Test
    void createKeyword() {
        //given
        String word = "전인혁";

        //when
        KeywordResponse response = keywordService.createKeyword(word);

        //then
        assertThat(response.getWord()).isEqualTo("전인혁");
    }

    private Keyword createdKeyword() {
        Keyword keyword = Keyword.builder()
            .word("전인혁")
            .build();
        return keywordRepository.save(keyword);
    }
}