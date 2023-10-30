package com.ssafy.vocabularyservice.api.service.vocabulary;

import com.ssafy.vocabularyservice.IntegrationTestSupport;
import com.ssafy.vocabularyservice.api.controller.vocabulary.response.WordResponse;
import com.ssafy.vocabularyservice.api.service.vocabulary.exception.DuplicateException;
import com.ssafy.vocabularyservice.domain.vocabulary.Vocabulary;
import com.ssafy.vocabularyservice.domain.vocabulary.repository.VocabularyRepository;
import com.ssafy.vocabularyservice.domain.word.Word;
import com.ssafy.vocabularyservice.domain.word.repository.WordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VocabularyServiceTest extends IntegrationTestSupport {

    @Autowired
    private VocabularyService vocabularyService;

    @Autowired
    private VocabularyRepository vocabularyRepository;

    @Autowired
    private WordRepository wordRepository;

    @DisplayName("이미 단어장에 등록된 단어라면 예외가 발생한다.")
    @Test
    void createVocabularyDuplicateException() {
        //given
        String memberKey = UUID.randomUUID().toString();
        Word word = createdWord();
        Vocabulary vocabulary = createdVocabulary(memberKey, word, false);

        //when //then
        assertThatThrownBy(() -> vocabularyService.createVocabulary(memberKey, word.getWordKey()))
            .isInstanceOf(DuplicateException.class)
            .hasMessage("이미 단어장에 등록된 단어입니다.");
    }

    @DisplayName("회원키와 단어키로 새로운 단어장을 등록한다.")
    @Test
    void createVocabulary() {
        //given
        String memberKey = UUID.randomUUID().toString();
        Word word = createdWord();

        //when
        WordResponse response = vocabularyService.createVocabulary(memberKey, word.getWordKey());

        //then
        assertThat(response)
            .extracting("wordKey", "content", "description")
            .containsExactlyInAnyOrder(
                "92288", "돼지",
                "멧돼짓과의 포유류. 몸무게는 200~250kg이며, 다리와 꼬리가 짧고 주둥이가 삐죽하다."
            );
    }

    @DisplayName("단어장 체크 여부를 미체크에서 체크로 수정한다.")
    @Test
    void checkVocabularyWithFalseToTrue() {
        //given
        String memberKey = UUID.randomUUID().toString();
        Word word = createdWord();
        Vocabulary vocabulary = createdVocabulary(memberKey, word, false);

        //when
        WordResponse response = vocabularyService.checkVocabulary(vocabulary.getId());

        //then
        assertThat(response.getCheck()).isTrue();
    }

    @DisplayName("단어장 체크 여부를 체크에서 미체크로 수정한다.")
    @Test
    void checkVocabularyWithTrueToFalse() {
        //given
        String memberKey = UUID.randomUUID().toString();
        Word word = createdWord();
        Vocabulary vocabulary = createdVocabulary(memberKey, word, true);

        //when
        WordResponse response = vocabularyService.checkVocabulary(vocabulary.getId());

        //then
        assertThat(response.getCheck()).isFalse();
    }

    @DisplayName("단어장 단어를 삭제한다.")
    @Test
    void removeVocabulary() {
        //given
        String memberKey = UUID.randomUUID().toString();
        Word word = createdWord();
        Vocabulary vocabulary = createdVocabulary(memberKey, word, true);

        //when
        WordResponse response = vocabularyService.removeVocabulary(vocabulary.getId());

        //then
        Optional<Vocabulary> findVocabulary = vocabularyRepository.findById(vocabulary.getId());
        assertThat(findVocabulary).isEmpty();
    }

    private Vocabulary createdVocabulary(String memberKey, Word word, boolean check) {
        Vocabulary vocabulary = Vocabulary.builder()
            .check(check)
            .memberKey(memberKey)
            .word(word)
            .build();
        return vocabularyRepository.save(vocabulary);
    }

    private Word createdWord() {
        Word word = Word.builder()
            .wordKey("92288")
            .content("돼지")
            .description("멧돼짓과의 포유류. 몸무게는 200~250kg이며, 다리와 꼬리가 짧고 주둥이가 삐죽하다.")
            .build();
        return wordRepository.save(word);
    }
}