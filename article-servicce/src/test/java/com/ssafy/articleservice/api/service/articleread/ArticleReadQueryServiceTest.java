package com.ssafy.articleservice.api.service.articleread;

import com.ssafy.articleservice.IntegrationTestSupport;
import com.ssafy.articleservice.api.controller.article.response.ArticleReadResponse;
import com.ssafy.articleservice.domain.article.Article;
import com.ssafy.articleservice.domain.article.repository.ArticleRepository;
import com.ssafy.articleservice.domain.articleread.ArticleRead;
import com.ssafy.articleservice.domain.articleread.repository.ArticleReadRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ArticleReadQueryServiceTest extends IntegrationTestSupport {

    @Autowired
    private ArticleReadQueryService articleReadQueryService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleReadRepository articleReadRepository;

    @DisplayName("회원 고유키를 입력받아 읽은 뉴스 기사 목록을 조회한다.")
    @Test
    void getMyArticleRead() {
        //given
        String memberKey = UUID.randomUUID().toString();
        Article article = createArticle();
        createArticleRead(memberKey, article);
        createArticleRead(memberKey, article);
        createArticleRead(memberKey, article);

        PageRequest pageRequest = PageRequest.of(0, 10);

        //when
        Page<ArticleReadResponse> response = articleReadQueryService.getMyArticleRead(memberKey, pageRequest);

        //then
        assertThat(response.getContent()).hasSize(3);
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

    private ArticleRead createArticleRead(String memberKey, Article article) {
        ArticleRead articleRead = ArticleRead.builder()
            .memberKey(memberKey)
            .article(article)
            .build();
        return articleReadRepository.save(articleRead);
    }
}