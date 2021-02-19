package com.javamaster.b2c.cloud.test.summer.service.impl;

import com.javamaster.b2c.cloud.test.summer.service.HelloService;
import org.summerframework.summer.core.anno.SummerService;

/**
 * @author yudong
 * @date 2019/5/13
 */
@SummerService
public class HelloServiceBackupImpl implements HelloService {

    @Override
    public String sayHello() {
        return "welcome to java";
    }

}
