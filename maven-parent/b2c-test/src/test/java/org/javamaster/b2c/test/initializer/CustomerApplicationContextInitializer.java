package org.javamaster.b2c.test.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
public class CustomerApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        log.info("{},{}", configurableApplicationContext.getBeanFactory().getBeanDefinitionCount(), configurableApplicationContext.getId());
    }
}
