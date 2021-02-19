package com.javamaster.b2c.cloud.test.scheduled;

import com.javamaster.b2c.cloud.test.scheduled.respsitory.SpringSchuduledCronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author yudong
 */
@EnableScheduling
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
@ComponentScan(basePackages = "com.javamaster.b2c")
@SpringBootApplication
public class ScheduledApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScheduledApplication.class, args);
    }

    @Autowired
    SpringSchuduledCronRepository cronRepository;

    @Override
    public void run(String... args) {
        System.out.println(cronRepository.findByCronId(0));
    }
}
