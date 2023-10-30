package com.ssafy.keywordservice.api.service.articlekeyword;

import com.ssafy.keywordservice.IntegrationTestSupport;
import com.ssafy.keywordservice.api.controller.articlekeyword.response.ArticleKeywordResponse;
import com.ssafy.keywordservice.domain.keyword.Keyword;
import com.ssafy.keywordservice.domain.keyword.repository.KeywordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleKeywordServiceTest extends IntegrationTestSupport {

    @Autowired
    private ArticleKeywordService articleKeywordService;

    @Autowired
    private KeywordRepository keywordRepository;

    @DisplayName("등록되지 않은 키워드를 등록한다면 예외가 발생한다.")
    @Test
    void createArticleKeywordWithoutKeyword() {
        //given
        Long articleKey = 1L;

        //when //then
        assertThatThrownBy(() -> articleKeywordService.createArticleKeyword(articleKey, "홍진식"))
            .isInstanceOf(NoSuchElementException.class)
            .hasMessage("등록된 키워드가 없습니다.");
    }

    @DisplayName("기사 고유키와 단어를 입력받아 뉴스 기사 키워드를 등록한다.")
    @Test
    void createArticleKeyword() {
        //given
        Keyword keyword = createKeyword();

        Long articleKey = 34645L;
        String word = "홍진식";

        //when
        ArticleKeywordResponse response = articleKeywordService.createArticleKeyword(articleKey, word);

        //then
        assertThat(response.getWord()).isEqualTo("홍진식");
    }

    private Keyword createKeyword() {
        Keyword keyword = Keyword.builder()
            .word("홍진식")
            .build();
        return keywordRepository.save(keyword);
    }
}