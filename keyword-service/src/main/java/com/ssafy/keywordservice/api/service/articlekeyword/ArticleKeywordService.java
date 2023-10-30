package com.ssafy.keywordservice.api.service.articlekeyword;

import com.ssafy.keywordservice.api.controller.articlekeyword.response.ArticleKeywordResponse;
import com.ssafy.keywordservice.domain.articlekeyword.ArticleKeyword;
import com.ssafy.keywordservice.domain.articlekeyword.repository.ArticleKeywordRepository;
import com.ssafy.keywordservice.domain.keyword.Keyword;
import com.ssafy.keywordservice.domain.keyword.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ArticleKeywordService {

    private final ArticleKeywordRepository articleKeywordRepository;
    private final KeywordRepository keywordRepository;

    public ArticleKeywordResponse createArticleKeyword(Long articleKey, String word) {
        Keyword findKeyword = getKeywordEntity(word);

        ArticleKeyword savedArticleKeyword = saveEntity(articleKey, findKeyword);

        return ArticleKeywordResponse.of(savedArticleKeyword);
    }

    private Keyword getKeywordEntity(String word) {
        Optional<Keyword> findKeyword = keywordRepository.findByWord(word);
        if (findKeyword.isEmpty()) {
            throw new NoSuchElementException("등록된 키워드가 없습니다.");
        }
        return findKeyword.get();
    }

    private ArticleKeyword saveEntity(Long articleKey, Keyword findKeyword) {
        ArticleKeyword articleKeyword = ArticleKeyword.builder()
            .articleKey(articleKey)
            .keyword(findKeyword)
            .build();
        return articleKeywordRepository.save(articleKeyword);
    }
}
