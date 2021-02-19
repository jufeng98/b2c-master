package org.javamaster.b2c.test.spring;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.test.config.MyTestsConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyTestsConfiguration.class)
@ActiveProfiles({"dev", "integration"})
public class ActiveProfilesTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void exampleTest() {
        log.info("{}", Arrays.toString(applicationContext.getEnvironment().getActiveProfiles()));
    }

}
