package com.ssafy.keywordservice.api.service.keyword;

import com.ssafy.keywordservice.api.controller.keyword.response.KeywordResponse;
import com.ssafy.keywordservice.api.service.keyword.exception.DuplicateException;
import com.ssafy.keywordservice.domain.keyword.Keyword;
import com.ssafy.keywordservice.domain.keyword.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public KeywordResponse createKeyword(String word) {

        Optional<Long> findKeyword = keywordRepository.existByWord(word);
        if (findKeyword.isPresent()) {
            throw new DuplicateException("이미 등록된 키워드 입니다.");
        }

        Keyword keyword = Keyword.builder()
            .word(word)
            .build();
        Keyword savedKeyword = keywordRepository.save(keyword);

        return KeywordResponse.of(savedKeyword);
    }
}
