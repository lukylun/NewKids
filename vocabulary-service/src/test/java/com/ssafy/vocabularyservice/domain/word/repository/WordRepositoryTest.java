package com.ssafy.vocabularyservice.domain.word.repository;

import com.ssafy.vocabularyservice.IntegrationTestSupport;
import com.ssafy.vocabularyservice.domain.word.Word;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class WordRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private WordRepository wordRepository;

    @DisplayName("단어키로 단어를 조회한다.")
    @Test
    void findByWordKey() {
        //given
        Word word = createdWord();

        //when
        Optional<Word> findWord = wordRepository.findByWordKey(word.getWordKey());

        //then
        assertThat(findWord).isPresent();
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