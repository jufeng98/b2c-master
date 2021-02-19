package com.javamaster.b2c.cloud.test.common.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yu on 2018/4/27.
 */
@RestController
@RequestMapping("/session/*")
public class SessionController {
    @RequestMapping("get/{key}")
    public Object getAttribute(HttpSession session, @PathVariable String key) {
        System.out.println(session.getId());
        Map<String,Object> map = new HashMap<>(6);
        map.put(key,session.getAttribute(key));
        return map;
    }

    @RequestMapping("set/{key}/{value}")
    public Object setAttribute(HttpSession session, @PathVariable String key, @PathVariable String value) {
        System.out.println(session.getId());
        session.setAttribute(key, value);
        return "success";
    }
}
