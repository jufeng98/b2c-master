package com.javamaster.b2c.cloud.test.hystrix.controller;

import com.javamaster.b2c.cloud.test.hystrix.service.VersionFeignService;
import com.javamaster.b2c.cloud.test.hystrix.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author yu
 */
@RestController
@RequestMapping("/version")
public class VersionController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private VersionService versionService;
    @Autowired
    private VersionFeignService versionFeignService;

    @RequestMapping(value = "/zuul/normal", method = {RequestMethod.GET})
    public String versionZuulNormal() {
        return restTemplate.getForEntity("http://b2c-cloud-test-zuul/zuul/info", String.class).getBody();
    }

    @RequestMapping(value = "/zuul/fallback", method = {RequestMethod.GET})
    public String versionZuul() {
        return versionService.versionZuul();
    }

    @RequestMapping(value = "/zuul/feign", method = {RequestMethod.GET})
    public String versionRedis() {
        return versionFeignService.versionZuul();
    }
}
