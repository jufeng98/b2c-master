package org.javamaster.b2c.test.controller;

import org.javamaster.b2c.test.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RemoteService remoteService;

    @GetMapping("/getName")
    private String getName(String mobile) {
        return remoteService.getUserNameByMobile(mobile);
    }
}
