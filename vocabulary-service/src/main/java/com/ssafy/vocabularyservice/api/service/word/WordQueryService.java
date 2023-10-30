package com.ssafy.vocabularyservice.api.service.word;

import com.ssafy.vocabularyservice.api.controller.word.response.WordResponse;
import com.ssafy.vocabularyservice.domain.word.repository.WordQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 단어 쿼리 서비스
 * @author 임우택
 */
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class WordQueryService {

    private final WordQueryRepository wordQueryRepository;

    /**
     * 단어 내용이 포함된 단어를 페이징 조건으로 조회
     * @param content 조회할 단어 내용
     * @param pageable 페이징 처리 내용
     * @return 조회된 단어 정보
     */
    public Page<WordResponse> getWords(String content, Pageable pageable) {

        List<WordResponse> responses = wordQueryRepository.findAllContentLike(content, pageable);

        long count = wordQueryRepository.countByContentLike(content);

        return new PageImpl<>(responses, pageable, count);
    }

}

