package com.ssafy.openapiservice.api.service.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.openapiservice.api.controller.openapi.response.WordResponse;
import com.ssafy.openapiservice.client.KoreaVocabularyClient;
import com.ssafy.openapiservice.client.mapper.KoreaVocabularyResponse;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpenApiService {

    private final KoreaVocabularyClient koreaVocabularyClient;
    private final Environment env;
    private final String CERTKEY_NO;
    private final String KEY;
    private final String TYPE_SEARCH;
    private final String REQ_TYPE;

    public OpenApiService(KoreaVocabularyClient koreaVocabularyClient, Environment env) {
        this.koreaVocabularyClient = koreaVocabularyClient;
        this.env = env;
        this.CERTKEY_NO = this.env.getProperty("openapi.certkeyNo");
        this.KEY = this.env.getProperty("openapi.key");
        this.TYPE_SEARCH = this.env.getProperty("openapi.typeSearch");
        this.REQ_TYPE = this.env.getProperty("openapi.reqType");
    }

    public List<WordResponse> searchDictionary(String query) {
        String json = koreaVocabularyClient.getKoreaVocabularyData(CERTKEY_NO, KEY, TYPE_SEARCH, REQ_TYPE, query);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            KoreaVocabularyResponse response = objectMapper.readValue(json, KoreaVocabularyResponse.class);

            return response.getChannel().getItem().stream()
                .map(WordResponse::of)
                .collect(Collectors.toList());

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
