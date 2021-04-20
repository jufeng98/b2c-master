package org.javamaster.b2c.spring.aop.service.impl;

import org.javamaster.b2c.spring.aop.service.AopService;
import org.springframework.stereotype.Service;

/**
 * @author yudong
 * @date 2021/4/20
 */
@Service
public class AopServiceImpl implements AopService {

    @Override
    public String getSimpleName() {
        return "hello world";
    }

}
