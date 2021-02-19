package com.javamaster.b2c.cloud.test.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping(value = "login", method = {RequestMethod.GET})
    public String login(HttpServletRequest request, String name, String password) {

        return null;

    }

    @RequestMapping(value = "testNotLogin", method = {RequestMethod.GET})
    public String testNotLogin(HttpServletRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails);
        return "insert success";

    }
}
