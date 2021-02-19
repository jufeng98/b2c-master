package org.javamaster.b2c.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author yudong
 * @date 2020/7/1
 */
@SpringBootApplication
@EnableMongoRepositories(basePackages = "org.javamaster.b2c.mongodb.repository")
public class MongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }

}