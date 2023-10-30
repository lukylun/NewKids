package com.ssafy.articleservice.api.service.articleread;

import com.ssafy.articleservice.api.controller.article.response.ArticleReadResponse;
import com.ssafy.articleservice.domain.articleread.repository.ArticleReadQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ArticleReadQueryService {

    private final ArticleReadQueryRepository articleReadQueryRepository;

    /**
     * 읽은 뉴스 기사 조회
     * @param memberKey 조회할 회원 고유키
     * @param pageable 조회할 페이지 정보
     * @return 조회된 읽은 뉴스 기사 목록
     */
    public Page<ArticleReadResponse> getMyArticleRead(String memberKey, Pageable pageable) {
        List<ArticleReadResponse> content = articleReadQueryRepository.findByMemberKey(memberKey, pageable);
        long totalCount = articleReadQueryRepository.getTotalCount(memberKey);
        return new PageImpl<>(content, pageable, totalCount);
    }
}
