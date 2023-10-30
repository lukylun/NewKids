package com.ssafy.vocabularyservice.api.service.word;

import com.ssafy.vocabularyservice.api.controller.word.response.WordResponse;
import com.ssafy.vocabularyservice.api.service.word.dto.CreateWordDto;
import com.ssafy.vocabularyservice.api.service.word.dto.EditWordDto;
import com.ssafy.vocabularyservice.api.service.word.exception.DuplicateException;
import com.ssafy.vocabularyservice.domain.word.Word;
import com.ssafy.vocabularyservice.domain.word.repository.WordQueryRepository;
import com.ssafy.vocabularyservice.domain.word.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * 단어 명령 서비스
 * @author 임우택
 */
@RequiredArgsConstructor
@Service
@Transactional
public class WordService {

    private final WordRepository wordRepository;
    private final WordQueryRepository wordQueryRepository;

    /**
     * 단어 등록
     * @param dto 등록할 단어의 정보
     * @return 등록된 단어의 정보
     * @throws DuplicateException 단어 키가 이미 존재하는 경우
     */
    public WordResponse createWord(CreateWordDto dto) {
        checkWordKeyDuplication(dto);

        Word savedWord = saveEntity(dto);

        return WordResponse.of(savedWord);
    }

    /**
     * 단어 수정
     * @param wordKey 수정할 단어의 단어키
     * @param dto 수정할 단어 정보
     * @return 수정된 단어 정보
     * @throws NoSuchElementException 등록되지 않은 단어인 경우
     */
    public WordResponse editWord(String wordKey, EditWordDto dto) {
        Word findWord = getEntity(wordKey);

        findWord.edit(dto.getContent(), dto.getDescription());

        return WordResponse.of(findWord);
    }

    /**
     * 단어 삭제
     * @param wordKey 삭제할 단어의 단어키
     * @return 삭제된 단어의 정보
     * @throws NoSuchElementException 등록되지 않은 단어인 경우
     */
    public WordResponse removeWord(String wordKey) {
        Word findWord = getEntity(wordKey);

        wordRepository.delete(findWord);

        return WordResponse.of(findWord);
    }

    private void checkWordKeyDuplication(CreateWordDto dto) {
        boolean isExistWordKey = wordQueryRepository.existWordKey(dto.getWordKey());
        if (isExistWordKey) {
            throw new DuplicateException("이미 사용중인 단어키입니다.");
        }
    }

    private Word saveEntity(CreateWordDto dto) {
        Word word = dto.toEntity();
        return wordRepository.save(word);
    }

    private Word getEntity(String wordKey) {
        Optional<Word> findWord = wordRepository.findByWordKey(wordKey);
        if (findWord.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 단어입니다.");
        }
        return findWord.get();
    }
}

