package org.javamaster.b2c.test;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.test.config.DatabaseConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.util.AopTestUtils;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfiguration.class)
public class TestingUtilitiesTests {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void aopTest() {
        Object myTestsConfiguration = applicationContext.getBean("myTestsConfiguration");

        Object targetObject = AopTestUtils.getTargetObject(myTestsConfiguration);
        log.info("{}", targetObject);

        Class<?> targetClass = AopUtils.getTargetClass(myTestsConfiguration);
        log.info("{}", targetClass);

        Class<?> aClass = AopProxyUtils.ultimateTargetClass(myTestsConfiguration);
        log.info("{}", aClass);
    }

    @Test
    public void reflectionTest() {
        log.info(applicationContext.getId());

        log.info("{}", ReflectionTestUtils.getField(applicationContext, "customClassLoader"));
    }

    @Test
    public void jdbcTest() {
        int rows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "actor");
        log.info("rows:{}", rows);
    }

}
