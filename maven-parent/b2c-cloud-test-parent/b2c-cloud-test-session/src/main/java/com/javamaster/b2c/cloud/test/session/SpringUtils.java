package com.javamaster.b2c.cloud.test.session;

import java.io.Serializable;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements ApplicationContextAware {

    public static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.context = applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static RedisTemplate<String, Serializable> redisTemplate() {
        return context.getBean(RedisTemplate.class);
    }
}
