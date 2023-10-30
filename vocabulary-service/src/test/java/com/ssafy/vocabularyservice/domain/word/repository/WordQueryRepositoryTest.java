package com.ssafy.vocabularyservice.domain.word.repository;

import com.ssafy.vocabularyservice.IntegrationTestSupport;
import com.ssafy.vocabularyservice.api.controller.word.response.WordResponse;
import com.ssafy.vocabularyservice.domain.word.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WordQueryRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private WordQueryRepository wordQueryRepository;

    @Autowired
    private WordRepository wordRepository;

    @DisplayName("단어 키가 존재하는지 알 수 있다.")
    @Test
    void existWordKey() {
        //given

        //when
        boolean isExistWordKey = wordQueryRepository.existWordKey("92288");

        //then
        assertThat(isExistWordKey).isFalse();
    }

    @DisplayName("단어명이 포함된 단어들을 조회한다.")
    @Test
    void findAllContentLike() {
        //given
        createWord("92289", "돼지");
        createWord("92290", "흑돼지");
        createWord("92291", "돼지띠");
        createWord("92292", "황금돼지해");
        createWord("92293", "병아리");

        PageRequest pageRequest = PageRequest.of(0, 20);

        //when
        List<WordResponse> responses = wordQueryRepository.findAllContentLike("돼지", pageRequest);

        //then
        assertThat(responses).hasSize(4)
            .extracting("wordKey", "content")
            .containsExactlyInAnyOrder(
                tuple("92289", "돼지"),
                tuple("92290", "흑돼지"),
                tuple("92291", "돼지띠"),
                tuple("92292", "황금돼지해")
            );
    }

    @DisplayName("단어명이 포함된 단어의 수를 조회한다.")
    @Test
    void countByContentLike() {
        //given
        createWord("92289", "돼지");
        createWord("92290", "흑돼지");
        createWord("92291", "돼지띠");
        createWord("92292", "황금돼지해");
        createWord("92293", "병아리");

        //when
        long count = wordQueryRepository.countByContentLike("돼지");

        //then
        assertThat(count).isEqualTo(4);
    }

    private Word createWord(String wordKey, String content) {
        Word word = Word.builder()
            .wordKey(wordKey)
            .content(content)
            .description("단어 설명입니다.")
            .build();
        return wordRepository.save(word);
    }
}