package org.javamaster.b2c.test;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.test.config.MyTestsConfiguration;
import org.javamaster.b2c.test.config.PropertyTestConfig;
import org.javamaster.b2c.test.initializer.SetEnvApplicationContextInitializer;
import org.javamaster.b2c.test.service.UserService;
import org.javamaster.b2c.test.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author yudong
 * @date 2021/2/24
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        PropertyTestConfig.class,
        MyTestsConfiguration.class,
        UserServiceImpl.class
}, initializers = SetEnvApplicationContextInitializer.class)
public class ContextConfigurationTests {

    @Autowired
    private UserService userService;

    @Test
    @SneakyThrows
    public void selectActorsTest() {
        List<Map<String, String>> maps = userService.selectActors(Collections.singletonList(1));
        log.info("{}", maps);
    }

}
