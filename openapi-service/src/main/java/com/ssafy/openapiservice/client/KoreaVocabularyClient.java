package com.ssafy.openapiservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "external-api", url = "https://stdict.korean.go.kr/api/search.do")
public interface KoreaVocabularyClient {

    @GetMapping
    String getKoreaVocabularyData(
        @RequestParam(defaultValue = "5914") String certkey_no,
        @RequestParam(defaultValue = "0675BC0A123498105ED8CEDB2235A321") String key,
        @RequestParam(defaultValue = "search") String type_search,
        @RequestParam(defaultValue = "json") String req_type,
        @RequestParam String q
    );
}
