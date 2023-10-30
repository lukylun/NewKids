package com.ssafy.keywordbatch.domain.keyword;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "keyword_logs")
public class KeywordLogDocs {

    @Id
    private String id;
    private String memberKey;
    private Long keywordId;
    private LocalDateTime createdDate;

    @Builder
    public KeywordLogDocs(String memberKey, Long keywordId) {
        this.memberKey = memberKey;
        this.keywordId = keywordId;
        this.createdDate = LocalDateTime.now();
    }
}
