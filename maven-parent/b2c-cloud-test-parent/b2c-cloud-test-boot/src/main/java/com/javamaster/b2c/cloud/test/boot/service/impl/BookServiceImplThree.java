package com.javamaster.b2c.cloud.test.boot.service.impl;

import com.javamaster.b2c.cloud.test.boot.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

/**
 * @author yudong
 * @date 2022/4/10
 */
@Service
@SessionScope
public class BookServiceImplThree implements BookService {
    @Override
    public String desc(String name) {
        return "best";
    }
}
