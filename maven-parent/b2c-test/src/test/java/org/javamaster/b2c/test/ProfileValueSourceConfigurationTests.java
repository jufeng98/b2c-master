package org.javamaster.b2c.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

/**
 * @author yudong
 * @date 2021/2/26
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration
@ProfileValueSourceConfiguration(ProfileValueSourceConfigurationTests.MyProfileValueSource.class)
public class ProfileValueSourceConfigurationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @IfProfileValue(name = "username", value = "jufeng98")
    public void exampleTest() {
        log.info(applicationContext.getId());
    }

    @SuppressWarnings("NullableProblems")
    public static class MyProfileValueSource implements ProfileValueSource {

        @Override
        public String get(String key) {
            log.info("retrieve profile value,key is:{}", key);
            if (Objects.equals(key, "username")) {
                return "jufeng98";
            }
            return null;
        }

    }
}
