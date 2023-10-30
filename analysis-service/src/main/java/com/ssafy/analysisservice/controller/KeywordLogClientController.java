package com.ssafy.analysisservice.controller;

import com.ssafy.analysisservice.service.KeywordLogQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/analysis-service/api/client")
public class KeywordLogClientController {

    private final KeywordLogQueryService keywordLogQueryService;

    @GetMapping()
    public List<Long> getKe() {
        return null;
    }
}
