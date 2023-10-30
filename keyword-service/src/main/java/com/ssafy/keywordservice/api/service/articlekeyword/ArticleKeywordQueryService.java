package com.ssafy.keywordservice.api.service.articlekeyword;

import com.ssafy.keywordservice.api.controller.articlekeyword.response.ArticleKeywordResponse;
import com.ssafy.keywordservice.domain.articlekeyword.repository.ArticleKeywordQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ArticleKeywordQueryService {

    private final ArticleKeywordQueryRepository articleKeywordQueryRepository;

    public List<ArticleKeywordResponse> getArticleKeywords(Long articleId) {
        return articleKeywordQueryRepository.findByArticleKey(articleId);
    }

    public List<Long> getArticleKeywordIds(Long articleId) {
        return articleKeywordQueryRepository.findKeywordIdByArticleKey(articleId);
    }
}
