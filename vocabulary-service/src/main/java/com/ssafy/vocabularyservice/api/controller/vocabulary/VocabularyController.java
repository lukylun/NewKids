package com.ssafy.vocabularyservice.api.controller.vocabulary;

import com.ssafy.vocabularyservice.api.controller.ApiResponse;
import com.ssafy.vocabularyservice.api.controller.vocabulary.request.CreateVocabularyRequest;
import com.ssafy.vocabularyservice.api.controller.vocabulary.response.CheckVocabularyResponse;
import com.ssafy.vocabularyservice.api.controller.vocabulary.response.VocabularyResponse;
import com.ssafy.vocabularyservice.api.controller.vocabulary.response.WordResponse;
import com.ssafy.vocabularyservice.api.service.vocabulary.VocabularyQueryService;
import com.ssafy.vocabularyservice.api.service.vocabulary.VocabularyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Vocabulary API 컨트롤러
 *
 * @author 임우택
 */
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/vocabulary-service/api")
public class VocabularyController {

    private final VocabularyService vocabularyService;
    private final VocabularyQueryService vocabularyQueryService;

    /**
     * 단어장 등록 API
     *
     * @param request   등록할 단어의 정보
     * @param memberKey 등록할 회원키
     * @return 등록된 단어의 정보
     */
    @PostMapping("/{memberKey}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<WordResponse> createVocabulary(@Valid @RequestBody CreateVocabularyRequest request, @PathVariable String memberKey) {
        log.debug("call VocabularyController#createVocabulary");
        log.debug("memberKey={}", memberKey);
        log.debug("CreateVocabularyRequest={}", request);

        WordResponse response = vocabularyService.createVocabulary(memberKey, request.getWordKey());
        log.debug("response={}", response);

        return ApiResponse.created(response);
    }

    /**
     * 나의 단어장 목록 조회 API
     *
     * @param memberKey 조회할 회원 고유키
     * @return 조회된 단어장 정보
     */
    @GetMapping("/{memberKey}")
    public ApiResponse<Page<VocabularyResponse>> getMyVocabulary(
        @PathVariable String memberKey,
        @RequestParam(defaultValue = "1") Integer pageNum,
        @RequestParam(required = false) Boolean check
    ) {
        log.debug("call VocabularyController#getMyVocabulary");
        log.debug("memberKey={}", memberKey);

        PageRequest pageRequest = PageRequest.of(pageNum - 1, 10);

        Page<VocabularyResponse> response = vocabularyQueryService.getMyVocabulary(memberKey, check, pageRequest);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }

    @GetMapping("/{memberKey}/check-count")
    public ApiResponse<CheckVocabularyResponse> getMyVocabularyWithCheck(@PathVariable String memberKey) {
        log.debug("call VocabularyController#getMyVocabularyWithCheck");
        log.debug("memberKey={}", memberKey);

        CheckVocabularyResponse response = vocabularyQueryService.getMyVocabularyWithCheck(memberKey);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }

    /**
     * 단어장 체크 상태 변경 API
     *
     * @param vocabularyId 상태 변경할 단어장의 PK
     * @return 변경된 단어장 정보
     */
    @PatchMapping("/{vocabularyId}")
    public ApiResponse<WordResponse> checkVocabulary(@PathVariable Long vocabularyId) {
        log.debug("call VocabularyController#checkVocabulary");
        log.debug("vocabularyId={}", vocabularyId);

        WordResponse response = vocabularyService.checkVocabulary(vocabularyId);
        log.debug("response={}", response);

        return ApiResponse.found(response);
    }

    /**
     * 단어장 삭제 API
     *
     * @param vocabularyId 삭제할 단어장의 PK
     * @return 삭제된 단어장 정보
     */
    @DeleteMapping("/{vocabularyId}")
    public ApiResponse<WordResponse> removeVocabulary(@PathVariable Long vocabularyId) {
        log.debug("call VocabularyController#removeVocabulary");
        log.debug("vocabularyId={}", vocabularyId);

        WordResponse response = vocabularyService.removeVocabulary(vocabularyId);
        log.debug("response={}", response);

        return ApiResponse.found(response);
    }
}
