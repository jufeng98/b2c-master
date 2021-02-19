package com.javamaster.b2c.cloud.test.redis.config;

import com.javamaster.b2c.cloud.test.redis.interceptor.ContentInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created on 2018/8/25.<br/>
 *
 * @author yudong
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ContentInterceptor());
    }
}
