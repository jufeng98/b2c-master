package org.javamaster.b2c.mongodb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Configuration
@PropertySource("classpath:application-test.properties")
@EnableMongoRepositories(basePackages = "org.javamaster.b2c.mongodb.repository")
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String url;

    @Bean
    public MongoTemplate mongoTemplate() {
        SimpleMongoClientDatabaseFactory mongoDatabaseFactory = new SimpleMongoClientDatabaseFactory(url);
        return new MongoTemplate(mongoDatabaseFactory);
    }
}
