package com.ssafy.userservice.api.controller.docs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class RestDocsController {

    @GetMapping("/user-service/docs/index")
    public String restDocs() {
        return "index";
    }
}
