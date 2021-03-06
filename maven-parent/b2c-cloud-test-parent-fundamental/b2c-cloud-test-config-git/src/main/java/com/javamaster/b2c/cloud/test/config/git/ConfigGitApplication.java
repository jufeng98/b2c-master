package com.javamaster.b2c.cloud.test.config.git;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigGitApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigGitApplication.class, args);
    }

}
