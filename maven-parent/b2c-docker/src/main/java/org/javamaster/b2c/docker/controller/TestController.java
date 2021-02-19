package org.javamaster.b2c.docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yudong
 * @date 2020/5/12
 */
@RestController
public class TestController {

    @GetMapping("/index")
    public String test() {
        return "hello world";
    }
}
