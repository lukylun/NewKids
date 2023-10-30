package com.ssafy.vocabularyservice.api.controller.docs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Rest Docs API 컨트롤러
 *
 * @author 임우택
 */
@Controller
public class RestDocsController {

    /**
     * Rest Docs 문서 호출 API
     *
     * @return index.html
     */
    @GetMapping("/vocabulary-service/docs/index")
    public String restDocs() {
        return "index";
    }
}
