package com.ssafy.keywordservice.domain.keywordsearch;

import com.ssafy.keywordservice.domain.TimeBaseEntity;
import com.ssafy.keywordservice.domain.keyword.Keyword;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeywordSearch extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_search_id")
    private Long id;

    private int count;

    private String memberKey;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;

    @Builder
    private KeywordSearch(int count, String memberKey, Keyword keyword) {
        this.count = count;
        this.memberKey = memberKey;
        this.keyword = keyword;
    }

    //== 비즈니스 로직 ==//
    public void increaseCount() {
        this.count += 1;
    }
}
