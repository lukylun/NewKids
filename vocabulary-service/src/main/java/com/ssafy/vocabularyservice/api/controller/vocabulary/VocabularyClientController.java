package com.ssafy.vocabularyservice.api.controller.vocabulary;

import com.ssafy.vocabularyservice.api.controller.vocabulary.response.WordClientResponse;
import com.ssafy.vocabularyservice.api.service.vocabulary.VocabularyQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Vocabulary 서버 내부 통신 API
 *
 * @author 임우택
 */
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/vocabulary-service/api/client/{memberKey}")
public class VocabularyClientController {

    private final VocabularyQueryService vocabularyQueryService;

    /**
     * 어휘 퀴즈를 위한 나의 단어장 조회 API
     *
     * @param memberKey 조회할 회원 고유키
     * @return 나의 단어장에 저장된 랜덤한 단어 10개
     */
    @GetMapping
    public List<WordClientResponse> getMyVocabularyClient(@PathVariable String memberKey) {
        log.debug("call VocabularyClientController#getMyVocabulary");
        log.debug("memberKey={}", memberKey);

        List<WordClientResponse> responses = vocabularyQueryService.getMyVocabularyClient(memberKey);
        log.debug("responses={}", responses);

        return responses;
    }
}
