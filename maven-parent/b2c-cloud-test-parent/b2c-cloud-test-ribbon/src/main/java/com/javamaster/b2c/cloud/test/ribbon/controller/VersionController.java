package com.javamaster.b2c.cloud.test.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.javamaster.b2c.cloud.test.common.constant.ProjectConst;

@RestController
@RequestMapping("version")
public class VersionController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "config", method = {RequestMethod.GET})
    public String add() {
        return restTemplate.getForEntity(ProjectConst.CONFIG_SERVICE_PREFIX + "/info",
                String.class).getBody();
    }
}
