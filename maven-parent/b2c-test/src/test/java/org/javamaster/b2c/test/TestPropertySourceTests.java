package org.javamaster.b2c.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration
@TestPropertySource(value = "classpath:application-${env}.properties", properties = "timezone=GMT+8")
public class TestPropertySourceTests {

    static {
        System.setProperty("env", "test");
    }

    @Value("${server.port}")
    private int port;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test() {
        log.info("{},{}", applicationContext.getEnvironment().getProperty("server.port"), port);
    }

    @Test
    @Timed(millis = 1000)
    public void testProcessWithOneSecondTimeout() {
        // some logic that should not take longer than 1 second to run
        log.info("{},{}", applicationContext.getEnvironment().getProperty("server.port"), port);
    }

    @Test
    @Repeat(3)
    public void repeat() {
        log.info("{},{}", applicationContext.getEnvironment().getProperty("server.port"), port);
    }
}
