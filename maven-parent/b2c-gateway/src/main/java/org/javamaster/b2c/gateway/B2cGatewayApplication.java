package org.javamaster.b2c.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yudong
 * @date 2021/8/25
 */
@Slf4j
@SpringBootApplication
public class B2cGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(B2cGatewayApplication.class, args);
        log.info("url:{}", "http://localhost:5559");
    }

}
