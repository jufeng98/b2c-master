package org.javamaster.b2c.spring.aop.service.impl;

import org.javamaster.b2c.spring.aop.annos.AopLog;
import org.javamaster.b2c.spring.aop.service.SpringService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author yudong
 * @date 2020/6/4
 */
@Service
public class SpringServiceImpl implements SpringService {

    @Value("#{systemProperties['file.encoding']}")
    private String encoding;
    @Value("#{systemEnvironment['JAVA_HOME']}")
    private String home;

    @Override
    public String test() {
        System.out.println(encoding);
        System.out.println(home);
        return "test";
    }

    @Override
    @AopLog
    public String test1(String param) {
        return param;
    }

}
