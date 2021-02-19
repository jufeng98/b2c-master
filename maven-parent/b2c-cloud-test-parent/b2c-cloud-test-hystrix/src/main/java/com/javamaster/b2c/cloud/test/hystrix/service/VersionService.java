package com.javamaster.b2c.cloud.test.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author yu
 */
@Service
public class VersionService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "addFallback")
    public String versionZuul() {
        return restTemplate.getForEntity("http://b2c-cloud-test-zuul/zuul/info", String.class).getBody();
    }

    public String addFallback() {
        return "{\"success\":false}";
    }
}
