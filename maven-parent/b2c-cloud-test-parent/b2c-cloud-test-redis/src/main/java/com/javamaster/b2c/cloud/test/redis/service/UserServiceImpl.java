package com.javamaster.b2c.cloud.test.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by yu on 2018/4/4.
 */
public class UserServiceImpl {
    @Autowired
    private RedisTemplate<String, Object> template;

    public String checkToken(String token) {
        String user = (String) template.opsForHash().get("login:", token);
        return user;
    }

    public void updateToken(String token, String user) {
        long time = System.currentTimeMillis();
        template.opsForHash().put("login:", token, user);
        template.opsForZSet().add("recent:", token, time);
    }
}
