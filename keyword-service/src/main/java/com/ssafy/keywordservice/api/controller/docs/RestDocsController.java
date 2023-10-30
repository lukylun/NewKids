package com.ssafy.keywordservice.api.controller.docs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestDocsController {

    @GetMapping("/keyword-service/docs/index")
    public String docs() {
        return "index";
    }
}
