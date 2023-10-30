package com.ssafy.vocabularyservice.domain.word;

import com.ssafy.vocabularyservice.domain.TimeBaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 단어 entity
 *
 * @author 임우택
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Word extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_id")
    private Long id;

    @Column(unique = true, nullable = false, length = 10)
    private String wordKey;

    @Column(nullable = false, updatable = false, length = 50)
    private String content;

    @Lob
    @Column(nullable = false, updatable = false)
    private String description;

    @Builder
    private Word(String wordKey, String content, String description) {
        this.wordKey = wordKey;
        this.content = content;
        this.description = description;
    }

    //== 비즈니스 로직 ==//
    public void edit(String content, String description) {
        this.content = content;
        this.description = description;
    }
}

