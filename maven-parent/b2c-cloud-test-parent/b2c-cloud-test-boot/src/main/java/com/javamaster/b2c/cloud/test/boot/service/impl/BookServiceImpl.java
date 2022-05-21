package com.javamaster.b2c.cloud.test.boot.service.impl;

import com.javamaster.b2c.cloud.test.boot.service.BookService;
import org.springframework.aop.framework.AopContext;

/**
 * @author yudong
 * @date 2022/4/10
 */
public class BookServiceImpl implements BookService {
    @Override
    public String desc(String name) {
        System.out.println(AopContext.currentProxy());
        return "good " + name;
    }
}
