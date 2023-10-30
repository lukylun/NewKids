package com.ssafy.articleservice.api.controller.docs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestDocsController {

    @GetMapping("/article-service/docs/index")
    public String restDocs() {
        return "index";
    }
}
