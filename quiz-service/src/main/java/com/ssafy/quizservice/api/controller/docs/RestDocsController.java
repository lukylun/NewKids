package com.ssafy.quizservice.api.controller.docs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestDocsController {

    @GetMapping("/quiz-service/docs/index")
    public String restDocs() {
        return "index";
    }
}
