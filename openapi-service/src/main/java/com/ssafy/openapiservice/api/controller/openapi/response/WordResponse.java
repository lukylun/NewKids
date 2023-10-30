package com.ssafy.openapiservice.api.controller.openapi.response;

import com.ssafy.openapiservice.client.mapper.Item;
import lombok.Builder;
import lombok.Data;

@Data
public class WordResponse {

    private String wordKey;
    private String content;
    private String description;

    @Builder
    public WordResponse(String wordKey, String content, String description) {
        this.wordKey = wordKey;
        this.content = content;
        this.description = description;
    }

    public static WordResponse of(Item item) {
        return WordResponse.builder()
            .wordKey(item.getTarget_code())
            .content(item.getWord())
            .description(item.getSense().getDefinition())
            .build();
    }
}
