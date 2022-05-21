package com.javamaster.b2c.cloud.test.boot.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yudong
 * @date 2022/4/17
 */
@Component
public class UserHandler {

    @ResponseBody
    public String getMsg(@PathVariable String id) {
        return "hello " + id;
    }
}
