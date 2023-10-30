package com.ssafy.analysisservice.service;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class KeywordSortDto implements Comparable<KeywordSortDto> {

    private Long keywordId;
    private int count;

    @Builder
    public KeywordSortDto(Long keywordId, int count) {
        this.keywordId = keywordId;
        this.count = count;
    }

    @Override
    public int compareTo(KeywordSortDto other) {
        if (this.count < other.count) {
            return 1;
        }
        return 0;
    }
}
