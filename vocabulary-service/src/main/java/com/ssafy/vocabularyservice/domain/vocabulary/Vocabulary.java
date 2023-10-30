package com.ssafy.vocabularyservice.domain.vocabulary;

import com.ssafy.vocabularyservice.domain.TimeBaseEntity;
import com.ssafy.vocabularyservice.domain.word.Word;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vocabulary extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vocabulary_id")
    private Long id;

    @Column(name = "`check`", nullable = false)
    private Boolean check;

    @Column(length = 100)
    private String memberKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "word_id")
    private Word word;

    @Builder
    private Vocabulary(Boolean check, String memberKey, Word word) {
        this.check = check;
        this.memberKey = memberKey;
        this.word = word;
    }

    //== 비즈니스 로직 ==//
    public void changeCheck() {
        this.check = !check;
    }
}
