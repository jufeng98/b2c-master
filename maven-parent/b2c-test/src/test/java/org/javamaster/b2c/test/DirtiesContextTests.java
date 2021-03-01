package org.javamaster.b2c.test;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.test.config.MyTestsConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yudong
 * @date 2021/2/26
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyTestsConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DirtiesContextTests {

    @Autowired
    private ApplicationContext context;

    @Test
    public void test1() {
        log.info("context:{}", context);
    }

    @Test
    public void test2() {
        log.info("context:{}", context);
    }

}
