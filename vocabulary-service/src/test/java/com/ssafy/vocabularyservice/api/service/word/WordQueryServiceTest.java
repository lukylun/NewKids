package com.ssafy.vocabularyservice.api.service.word;


import com.ssafy.vocabularyservice.IntegrationTestSupport;
import com.ssafy.vocabularyservice.api.controller.word.response.WordResponse;
import com.ssafy.vocabularyservice.domain.word.Word;
import com.ssafy.vocabularyservice.domain.word.repository.WordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class WordQueryServiceTest extends IntegrationTestSupport {

    @Autowired
    private WordQueryService wordQueryService;

    @Autowired
    private WordRepository wordRepository;

    @DisplayName("단어 내용을 받아 단어 내용이 포함된 단어를 조회한다.")
    @Test
    void getWords() {
        //given
        createWord("92289", "돼지");
        createWord("92290", "흑돼지");
        createWord("92291", "돼지띠");
        createWord("92292", "황금돼지해");
        createWord("92293", "병아리");

        PageRequest pageRequest = PageRequest.of(0, 20);

        //when
        Page<WordResponse> pageResponse = wordQueryService.getWords("돼지", pageRequest);

        //then
        assertThat(pageResponse.getContent()).hasSize(4)
            .extracting("wordKey", "content")
            .containsExactlyInAnyOrder(
                tuple("92289", "돼지"),
                tuple("92290", "흑돼지"),
                tuple("92291", "돼지띠"),
                tuple("92292", "황금돼지해")
            );
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