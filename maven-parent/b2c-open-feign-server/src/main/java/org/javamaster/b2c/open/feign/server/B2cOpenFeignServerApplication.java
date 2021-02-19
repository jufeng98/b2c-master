package org.javamaster.b2c.open.feign.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yudong
 * @date 2020/7/31
 */
@EnableDiscoveryClient
@SpringBootApplication
public class B2cOpenFeignServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(B2cOpenFeignServerApplication.class, args);
    }

}
