package com.ssafy.vocabularyservice.domain.vocabulary.repository;

import com.ssafy.vocabularyservice.IntegrationTestSupport;
import com.ssafy.vocabularyservice.domain.word.Word;
import com.ssafy.vocabularyservice.domain.word.repository.WordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class VocabularyQueryRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private VocabularyQueryRepository vocabularyQueryRepository;

    @Autowired
    private WordRepository wordRepository;

    @DisplayName("회원키와 단어키로 단어장 존재 여부를 조회한다.")
    @Test
    void existVocabulary() {
        //given
        String memberKey = UUID.randomUUID().toString();
        Word word = createdWord();

        //when
        boolean result = vocabularyQueryRepository.existVocabulary(memberKey, word.getWordKey());

        //then
        assertThat(result).isFalse();
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