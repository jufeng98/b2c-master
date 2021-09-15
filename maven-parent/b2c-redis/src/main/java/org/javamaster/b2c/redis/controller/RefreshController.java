package org.javamaster.b2c.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

/**
 * @author yudong
 * @date 2021/9/15
 */
@RestController
public class RefreshController {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    private ContextRefresher contextRefresher;

    @GetMapping("/refresh")
    public String refresh() {
        System.setProperty("spring.redis.host", "192.168.234.33");
        System.setProperty("spring.redis.port", "56379");
        System.setProperty("spring.redis.password", "Mon56BuEcXzZ");
        contextRefresher.refresh();
        return "success";
    }

    @GetMapping("/getValue")
    public String getValue() {
        System.out.println(redisTemplate.opsForValue().get("hello"));
        redisTemplate.expire("hello", Duration.ofMinutes(30));
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        System.out.println(connectionFactory);
        return "success";
    }

}
