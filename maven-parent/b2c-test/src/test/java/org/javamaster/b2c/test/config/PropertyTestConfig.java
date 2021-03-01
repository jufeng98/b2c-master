package org.javamaster.b2c.test.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yudong
 * @date 2021/2/24
 */
@TestConfiguration
@PropertySource(value = "classpath:application-${env}.properties")
public class PropertyTestConfig {


}
