package org.javamaster.spring.test.config;

import static org.javamaster.spring.test.GeneralTestCode.PROFILE_UNIT_TEST;
import org.javamaster.spring.test.utils.ContextTestUtils;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * @author yudong
 * @date 2021/5/17
 */
@SuppressWarnings("all")
@TestConfiguration
@Profile(PROFILE_UNIT_TEST)
public class SysConfig {

    @Bean
    public ContextTestUtils contextTestUtils() {
        return new ContextTestUtils();
    }

}
