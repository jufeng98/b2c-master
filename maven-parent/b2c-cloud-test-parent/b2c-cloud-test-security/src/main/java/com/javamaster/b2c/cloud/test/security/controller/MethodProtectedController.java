package com.javamaster.b2c.cloud.test.security.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javamaster.b2c.cloud.test.security.entity.User;
import com.javamaster.b2c.cloud.test.security.repository.UserRepository;

@RestController
public class MethodProtectedController {
    @Autowired
    UserRepository repository;

    @RequestMapping(value = "secured", method = {RequestMethod.GET})
    public String secured(HttpServletRequest request, String name, String password) {
        System.out.println("secured controller");
        repository.secured(new User());
        return "1";
    }

    @RequestMapping(value = "rolesAllowed", method = {RequestMethod.GET})
    public String rolesAllowed(HttpServletRequest request, String name, String password) {
        System.out.println("rolesAllowed controller");
        repository.rolesAllowed(new User());
        return "1";
    }

    @RequestMapping(value = "preAuthorize", method = {RequestMethod.GET})
    public String preAuthorize(HttpServletRequest request, String name, String password) {
        System.out.println("preAuthorize controller");
        repository.preAuthorize(new User());
        return "1";
    }

    @RequestMapping(value = "postAuthorize", method = {RequestMethod.GET})
    public String postAuthorize(HttpServletRequest request, String name, String password) {
        System.out.println("postAuthorize controller");
        List<User> users = new ArrayList<>();
        users.add(new User());
        repository.postAuthorize(users);
        return "1";
    }

}
