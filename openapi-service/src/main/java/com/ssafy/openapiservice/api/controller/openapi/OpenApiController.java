package com.ssafy.openapiservice.api.controller.openapi;

import com.ssafy.openapiservice.api.controller.ApiResponse;
import com.ssafy.openapiservice.api.controller.openapi.response.WordResponse;
import com.ssafy.openapiservice.api.service.openapi.OpenApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/openapi-service/api/search")
public class OpenApiController {

    private final OpenApiService openApiService;

    @GetMapping
    public ApiResponse<List<WordResponse>> searchDictionary(@RequestParam String query) {
        log.debug("call OpenApiController#searchDictionary");

        List<WordResponse> response = openApiService.searchDictionary(query);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }

}
