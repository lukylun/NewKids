package com.ssafy.keywordservice.api.controller.keyword.response;

import com.ssafy.keywordservice.domain.keyword.Keyword;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class KeywordResponse {

    private Long keywordId;
    private String word;
    private String createdDate;

    @Builder
    public KeywordResponse(Long keywordId, String word, LocalDateTime createdDate) {
        this.keywordId = keywordId;
        this.word = word;
        this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
    }

    public static KeywordResponse of(Keyword keyword) {
        return KeywordResponse.builder()
            .keywordId(keyword.getId())
            .word(keyword.getWord())
            .createdDate(keyword.getCreatedDate())
            .build();
    }
}
