package com.ssafy.openapiservice.api.service.naver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.openapiservice.api.service.naver.dto.NaverDto;
import com.ssafy.openapiservice.client.NaverOpenApiClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class NaverOpenApiService {

    private final NaverOpenApiClient naverOpenApiClient;
    private final Environment env;
    private final String id;
    private final String secret;

    public NaverOpenApiService(NaverOpenApiClient naverOpenApiClient, Environment env) {
        this.naverOpenApiClient = naverOpenApiClient;
        this.env = env;
        this.id = this.env.getProperty("naver.id");
        this.secret = this.env.getProperty("naver.secret");
    }

    public String searchDictionary(String query) {
        String json = naverOpenApiClient.getVocabulary(id, secret, query);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            NaverDto naverDto = objectMapper.readValue(json, NaverDto.class);

            return naverDto.getItems().get(0).getDescription();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
