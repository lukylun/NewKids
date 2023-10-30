package com.ssafy.vocabularyservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.vocabularyservice.api.controller.vocabulary.VocabularyController;
import com.ssafy.vocabularyservice.api.controller.word.WordController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

//@WebMvcTest(controllers = {WordController.class, VocabularyController.class})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;
}
