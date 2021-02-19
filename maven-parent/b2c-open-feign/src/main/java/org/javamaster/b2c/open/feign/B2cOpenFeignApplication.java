package org.javamaster.b2c.open.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yudong
 * @date 2020/7/31
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class B2cOpenFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(B2cOpenFeignApplication.class, args);
    }

}
