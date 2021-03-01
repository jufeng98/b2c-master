package org.javamaster.b2c.test;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.test.config.WebMvcConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.AopTestUtils;

import java.util.Arrays;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles({"dev"})
@ContextConfiguration(classes = WebMvcConfig.class)
public class ActiveProfilesTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test

    public void aopTest() {
        log.info("active profiles:{}", Arrays.toString(applicationContext.getEnvironment().getActiveProfiles()));
        Object myTestsConfiguration = applicationContext.getBean("objectMapperDev");
        Object targetObject = AopTestUtils.getTargetObject(myTestsConfiguration);
        log.info("{}", targetObject);
    }

}
