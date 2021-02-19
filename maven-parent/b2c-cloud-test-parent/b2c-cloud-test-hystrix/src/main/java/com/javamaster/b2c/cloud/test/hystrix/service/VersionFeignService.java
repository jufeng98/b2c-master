package com.javamaster.b2c.cloud.test.hystrix.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yu
 */
@FeignClient(value = "http://b2c-cloud-test-zuul", fallback = VersionFeignHystrix.class)
public interface VersionFeignService {

    @RequestMapping("/info")
    String versionZuul();

}
