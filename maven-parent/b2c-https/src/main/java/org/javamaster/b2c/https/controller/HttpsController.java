package org.javamaster.b2c.https.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yudong
 * @date 2020/5/14
 */
@RestController
public class HttpsController {

    @GetMapping("/welcome")
    public String welcome() {
        return "hello world";
    }

}
