package org.javamaster.b2c.test.spring;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.test.config.MyTestsConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.AopTestUtils;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyTestsConfiguration.class)
public class CommonTestClassesTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void exampleTest() {
        log.info(applicationContext.getId());

        log.info("{}", ReflectionTestUtils.getField(applicationContext, "customClassLoader"));

        Object myTestsConfiguration = applicationContext.getBean("myTestsConfiguration");

        Object targetObject = AopTestUtils.getTargetObject(myTestsConfiguration);
        log.info("{}", targetObject);

        Class<?> targetClass = AopUtils.getTargetClass(myTestsConfiguration);
        log.info("{}", targetClass);

        Class<?> aClass = AopProxyUtils.ultimateTargetClass(myTestsConfiguration);
        log.info("{}", aClass);
    }

}
