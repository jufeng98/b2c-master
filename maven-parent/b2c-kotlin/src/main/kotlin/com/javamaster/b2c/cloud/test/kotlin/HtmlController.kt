package com.javamaster.b2c.cloud.test.kotlin

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * @author yudong
 * @date 2019/5/9
 */

@RestController
@RequestMapping("/kotlin")
class HtmlController {

    @GetMapping("/hello")
    fun blog(model: Model): String {
        return "hello world"
    }

}