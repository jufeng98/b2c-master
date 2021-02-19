package org.javamaster.b2c.test.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration
@ProfileValueSourceConfiguration(SystemProfileValueSource.class)
public class IfProfileValueTests {

    @Autowired
    private ApplicationContext applicationContext;


    @Test
    @IfProfileValue(name = "java.vendor", value = "Oracle Corporation")
    public void exampleTest() {
        log.info(applicationContext.getId());
    }

}
