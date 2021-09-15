package org.javamaster.b2c.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;

/**
 * @author yudong
 * @date 2020/9/15
 */
@SpringBootApplication(exclude = RedisAutoConfiguration.class)
public class B2cRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(B2cRedisApplication.class, args);
    }

}
