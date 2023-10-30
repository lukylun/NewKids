package com.ssafy.vocabularyservice.api.service.vocabulary;

import com.ssafy.vocabularyservice.api.controller.vocabulary.response.WordResponse;
import com.ssafy.vocabularyservice.api.service.vocabulary.exception.DuplicateException;
import com.ssafy.vocabularyservice.domain.vocabulary.Vocabulary;
import com.ssafy.vocabularyservice.domain.vocabulary.repository.VocabularyQueryRepository;
import com.ssafy.vocabularyservice.domain.vocabulary.repository.VocabularyRepository;
import com.ssafy.vocabularyservice.domain.word.Word;
import com.ssafy.vocabularyservice.domain.word.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * 단어장 명령 서비스
 *
 * @author 임우택
 */
@RequiredArgsConstructor
@Service
@Transactional
public class VocabularyService {

    private final VocabularyRepository vocabularyRepository;
    private final VocabularyQueryRepository vocabularyQueryRepository;
    private final WordRepository wordRepository;

    /**
     * 단어장 등록
     *
     * @param memberKey 등록할 회원키
     * @param workKey   등록할 단어키
     * @return 등록된 단어 정보
     * @throws DuplicateException     이미 단어장에 등록된 단어인 경우
     * @throws NoSuchElementException 존재하지 않는 단어키인 경우
     */
    public WordResponse createVocabulary(String memberKey, String workKey) {
        checkVocabularyDuplication(memberKey, workKey);

        Word findWord = getWordEntity(workKey);

        Vocabulary vocabulary = saveVocabularyEntity(memberKey, findWord);

        return WordResponse.of(vocabulary);
    }

    /**
     * 단어장 체크 수정
     *
     * @param vocabularyId 수정할 단어장의 PK
     * @return 수정된 딘어장 정보
     * @throws NoSuchElementException 존재하지 않는 단어장인 경우
     */
    public WordResponse checkVocabulary(Long vocabularyId) {
        Vocabulary findVocabulary = getVocabularyEntity(vocabularyId);

        findVocabulary.changeCheck();

        return WordResponse.of(findVocabulary);
    }

    public WordResponse removeVocabulary(Long vocabularyId) {
        Vocabulary findVocabulary = getVocabularyEntity(vocabularyId);

        vocabularyRepository.delete(findVocabulary);

        return WordResponse.of(findVocabulary);
    }

    private void checkVocabularyDuplication(String memberKey, String workKey) {
        boolean isExistVocabulary = vocabularyQueryRepository.existVocabulary(memberKey, workKey);
        if (isExistVocabulary) {
            throw new DuplicateException("이미 단어장에 등록된 단어입니다.");
        }
    }

    private Word getWordEntity(String workKey) {
        Optional<Word> findWord = wordRepository.findByWordKey(workKey);
        if (findWord.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 단어입니다.");
        }
        return findWord.get();
    }

    private Vocabulary saveVocabularyEntity(String memberKey, Word findWord) {
        Vocabulary vocabulary = Vocabulary.builder()
            .check(false)
            .memberKey(memberKey)
            .word(findWord)
            .build();
        return vocabularyRepository.save(vocabulary);
    }

    private Vocabulary getVocabularyEntity(Long vocabularyId) {
        Optional<Vocabulary> findVocabulary = vocabularyRepository.findById(vocabularyId);
        if (findVocabulary.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 단어장입니다.");
        }
        return findVocabulary.get();
    }
}
