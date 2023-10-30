package com.ssafy.articleservice.api.service.articleread;

import com.ssafy.articleservice.IntegrationTestSupport;
import com.ssafy.articleservice.api.controller.article.response.ArticleReadResponse;
import com.ssafy.articleservice.api.service.articleread.exception.DuplicateException;
import com.ssafy.articleservice.domain.article.Article;
import com.ssafy.articleservice.domain.article.repository.ArticleRepository;
import com.ssafy.articleservice.domain.articleread.ArticleRead;
import com.ssafy.articleservice.domain.articleread.repository.ArticleReadRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class ArticleReadServiceTest extends IntegrationTestSupport {

    @Autowired
    private ArticleReadService articleReadService;

    @Autowired
    private ArticleReadRepository articleReadRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @DisplayName("등록되지 않은 기사를 읽으면 예외가 발생한다.")
    @Test
    void createArticleReadWithoutArticle() {
        //given
        String memberKey = UUID.randomUUID().toString();

        //when //then
        assertThatThrownBy(() -> articleReadService.createArticleRead(memberKey, 1L))
            .isInstanceOf(NoSuchElementException.class)
            .hasMessage("등록되지 않은 뉴스 기사 입니다.");
    }

    @DisplayName("읽은 기사 내역이 이미 등록되어 있다면 예외가 발생한다.")
    @Test
    void createArticleReadDuplicate() {
        //given
        String memberKey = UUID.randomUUID().toString();
        Article article = createArticle();

        //when
        ArticleRead articleRead = ArticleRead.builder()
            .memberKey(memberKey)
            .article(article)
            .build();
        articleReadRepository.save(articleRead);

        // then
        assertThatThrownBy(() -> articleReadService.createArticleRead(memberKey, article.getId()))
            .isInstanceOf(DuplicateException.class)
            .hasMessage("이미 등록된 뉴스 기사 내역입니다.");
    }

    @DisplayName("회원 고유키와 기사 PK를 받아 읽은 기사 내역을 등록한다.")
    @Test
    void createArticleRead() {
        //given
        String memberKey = UUID.randomUUID().toString();
        Article article = createArticle();

        //when
        ArticleReadResponse response = articleReadService.createArticleRead(memberKey, article.getId());

        //then
        assertThat(response.getTitle()).isEqualTo("전인혁 오늘 지각으로 댄스에 당첨...");
    }

    private Article createArticle() {
        Article article = Article.builder()
            .title("전인혁 오늘 지각으로 댄스에 당첨...")
            .subTitle("과연 오늘은 어떤 춤을?!")
            .writer("임우택")
            .publishedDate(LocalDateTime.of(2023, 9, 18, 9, 0))
            .content("전인혁은 오늘 지각을 하였다. 아직 일어나지 않은 것 같다. 오늘의 춤을 선택해야지~")
            .thumbnailImg("http://전즈리얼.jpg")
            .build();
        return articleRepository.save(article);
    }
}