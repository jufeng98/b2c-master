package com.javamaster.b2c.cloud.test.eurake;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author yudong
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(EurekaApplication.class);
        application.setBannerMode(Mode.CONSOLE);
        application.run(args);
    }
}
