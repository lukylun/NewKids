package com.ssafy.vocabularyservice.api.controller.word;

import com.ssafy.vocabularyservice.api.controller.ApiResponse;
import com.ssafy.vocabularyservice.api.controller.word.request.CreateWordRequest;
import com.ssafy.vocabularyservice.api.controller.word.request.EditWordRequest;
import com.ssafy.vocabularyservice.api.controller.word.response.WordResponse;
import com.ssafy.vocabularyservice.api.service.word.WordQueryService;
import com.ssafy.vocabularyservice.api.service.word.WordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Word API 컨트롤러
 *
 * @author 임우택
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/vocabulary-service/api/words")
public class WordController {

    private final WordService wordService;
    private final WordQueryService wordQueryService;

    /**
     * 단어 등록 API
     *
     * @param request 등록할 단어의 정보
     * @return 201 CREATED 등록된 단어의 정보
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<WordResponse> createWord(@Valid @RequestBody CreateWordRequest request) {
        log.debug("call WordController#createWord");
        log.debug("CreateWordRequest={}", request);

        WordResponse response = wordService.createWord(request.toCreateWordDto());
        log.debug("response={}", response);

        return ApiResponse.created(response);
    }

    /**
     * 단어 조회 API
     * @param keyword 조회할 키워드
     * @param pageNum 조회할 페이지 번호
     * @return 200 조회된 단어 정보
     */
    @GetMapping
    public ApiResponse<Page<WordResponse>> getWords(
        @RequestParam(defaultValue = "") String keyword,
        @RequestParam(defaultValue = "1") Integer pageNum
    ) {
        log.debug("call WordController#getWords");
        log.debug("keyword={}, pageNum={}", keyword, pageNum);

        PageRequest pageRequest = PageRequest.of(pageNum - 1, 20);
        Page<WordResponse> response = wordQueryService.getWords(keyword, pageRequest);
        log.debug("response={}", response.getContent());

        return ApiResponse.ok(response);
    }

    /**
     * 단어 수정 API
     * @param wordKey 수정할 단어의 단어키
     * @param request 수정할 단어의 정보
     * @return 302 수정된 단어의 정보
     */
    @PatchMapping("/{wordKey}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<WordResponse> editWord(@PathVariable String wordKey, @Valid @RequestBody EditWordRequest request) {
        log.debug("call WordController#createWord");
        log.debug("EditWordRequest={}", request);

        WordResponse response = wordService.editWord(wordKey, request.toEditWordDto());
        log.debug("response={}", response);

        return ApiResponse.found(response);
    }

    /**
     * 단어 삭제 API
     * @param wordKey 삭제할 단어의 단어키
     * @return 302 삭제된 단어의 정보
     */
    @DeleteMapping("/{wordKey}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<WordResponse> removeWord(@PathVariable String wordKey) {
        log.debug("call WordController#removeWord");

        WordResponse response = wordService.removeWord(wordKey);
        log.debug("response={}", response);

        return ApiResponse.found(response);
    }
}
