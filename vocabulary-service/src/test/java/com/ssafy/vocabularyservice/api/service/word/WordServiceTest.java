package com.ssafy.vocabularyservice.api.service.word;

import com.ssafy.vocabularyservice.IntegrationTestSupport;
import com.ssafy.vocabularyservice.api.controller.word.response.WordResponse;
import com.ssafy.vocabularyservice.api.service.word.dto.CreateWordDto;
import com.ssafy.vocabularyservice.api.service.word.dto.EditWordDto;
import com.ssafy.vocabularyservice.api.service.word.exception.DuplicateException;
import com.ssafy.vocabularyservice.domain.word.Word;
import com.ssafy.vocabularyservice.domain.word.repository.WordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class WordServiceTest extends IntegrationTestSupport {

    @Autowired
    private WordService wordService;

    @Autowired
    private WordRepository wordRepository;

    @DisplayName("단어키가 이미 존재하는 경우 중복 예외가 발생한다.")
    @Test
    void createWordWithDuplicateWordKey() {
        //given
        Word word = createdWord();
        CreateWordDto dto = CreateWordDto.builder()
            .wordKey("92288")
            .content("홍진식")
            .description("멧돼짓과의 포유류. 몸무게는 100~150kg이며, 다리와 꼬리가 짧고 주둥이가 삐죽하다.")
            .build();

        //when //then
        assertThatThrownBy(() -> wordService.createWord(dto))
            .isInstanceOf(DuplicateException.class)
            .hasMessage("이미 사용중인 단어키입니다.");
    }

    @DisplayName("새로운 단어를 등록할 수 있다.")
    @Test
    void createWord() {
        //given
        CreateWordDto dto = CreateWordDto.builder()
            .wordKey("92288")
            .content("돼지")
            .description("멧돼짓과의 포유류. 몸무게는 200~250kg이며, 다리와 꼬리가 짧고 주둥이가 삐죽하다.")
            .build();

        //when
        WordResponse response = wordService.createWord(dto);

        //then
        assertThat(response)
            .extracting("wordKey", "content", "description")
            .containsExactlyInAnyOrder(
                "92288",
                "돼지",
                "멧돼짓과의 포유류. 몸무게는 200~250kg이며, 다리와 꼬리가 짧고 주둥이가 삐죽하다."
            );
    }

    @DisplayName("등록되지 않은 단어를 수정하려는 경우 예외가 발생한다.")
    @Test
    void editWordWithNoWord() {
        //given
        EditWordDto dto = EditWordDto.builder()
            .content("변경된 단어")
            .description("변경된 내용입니다.")
            .build();

        //when //then
        assertThatThrownBy(() -> wordService.editWord("12345", dto))
            .isInstanceOf(NoSuchElementException.class)
            .hasMessage("등록되지 않은 단어입니다.");
    }

    @DisplayName("등록된 단어를 수정한다.")
    @Test
    void editWord() {
        //given
        Word word = createdWord();
        EditWordDto dto = EditWordDto.builder()
            .content("변경된 단어")
            .description("변경된 내용입니다.")
            .build();

        //when
        WordResponse response = wordService.editWord(word.getWordKey(), dto);

        //then
        assertThat(response)
            .extracting("wordKey", "content", "description")
            .containsExactlyInAnyOrder(
                "92288",
                "변경된 단어",
                "변경된 내용입니다."
            );
    }

    @DisplayName("등록된 단어를 삭제한다.")
    @Test
    void removeWord() {
        //given
        Word word = createdWord();

        //when
        WordResponse response = wordService.removeWord(word.getWordKey());

        //then
        Optional<Word> findWord = wordRepository.findByWordKey(word.getWordKey());
        assertThat(findWord).isEmpty();
        assertThat(response)
            .extracting("wordKey", "content", "description")
            .containsExactlyInAnyOrder(
                "92288",
                "돼지",
                "멧돼짓과의 포유류. 몸무게는 200~250kg이며, 다리와 꼬리가 짧고 주둥이가 삐죽하다."
            );
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