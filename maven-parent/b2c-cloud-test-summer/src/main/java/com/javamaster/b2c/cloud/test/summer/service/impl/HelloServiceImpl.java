package com.javamaster.b2c.cloud.test.summer.service.impl;

import com.javamaster.b2c.cloud.test.summer.service.HelloService;
import org.summerframework.summer.core.anno.SummerAutowired;
import org.summerframework.summer.core.anno.SummerService;
import org.summerframework.summer.core.ioc.SummerApplicationContext;
import org.summerframework.summer.core.ioc.SummerApplicationContextAware;
import org.summerframework.summer.core.ioc.SummerBeanNameAware;
import org.summerframework.summer.core.ioc.SummerInitializingBean;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

/**
 * @author yudong
 * @date 2019/5/13
 */
@SummerService
public class HelloServiceImpl implements HelloService, SummerBeanNameAware, SummerApplicationContextAware, SummerInitializingBean {
    Logger logger = Logger.getLogger(getClass().getName());

    @SummerAutowired
    private SummerApplicationContext context;

    @Override
    public String sayHello() {
        return "hello world";
    }

    @PostConstruct
    private void init() {
        logger.info("PostConstruct init called,bean method result:" + context.getBean(HelloServiceImpl.class).sayHello());
    }

    @Override
    public void setApplicationContext(SummerApplicationContext context) {
        logger.info("setApplicationContext called");
    }

    @Override
    public void setBeanNameAware(String beanNameAware) {
        logger.info("setBeanNameAware called");
    }

    @Override
    public void afterPropertiesSet() {
        logger.info("afterPropertiesSet called");
    }
}
