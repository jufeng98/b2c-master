package com.javamaster.b2c.cloud.test.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author yudong
 */
@ImportResource(locations = {"classpath*:spring/b2c-servlet.xml"})
@EnableDiscoveryClient
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
@ComponentScan(basePackages = {"com.javamaster.b2c"})
@SpringBootApplication(exclude = EurekaClientAutoConfiguration.class)
public class BootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BootApplication.class, args);
        ClassLoader classLoader = context.getClassLoader();
        System.out.println(classLoader);
    }

}
