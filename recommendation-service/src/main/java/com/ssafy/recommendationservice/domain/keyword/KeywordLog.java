package com.ssafy.recommendationservice.domain.keyword;

import com.ssafy.recommendationservice.domain.TimeBaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeywordLog extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_log_id")
    private Long id;
    private Long keywordId;
    private int count;

    @Builder
    private KeywordLog(Long id, Long keywordId, int count) {
        this.id = id;
        this.keywordId = keywordId;
        this.count = count;
    }
}
