package org.javamaster.b2c.test.spring;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.test.config.MyTestsConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
// 只适用于junit5
// @SpringJUnitConfig(MyTestsConfiguration.class)
public class SpringJUnitConfigTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void exampleTest() {
        log.info(applicationContext.getId());
    }

}
