package org.javamaster.b2c.test.spring;

import org.javamaster.b2c.test.config.MyTestsConfiguration;
import org.javamaster.b2c.test.initializer.CustomerApplicationContextInitializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yudong
 * @date 2020/10/23
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = MyTestsConfiguration.class,
        initializers = CustomerApplicationContextInitializer.class
)
public class ApplicationContextInitializersTests {

    @Test
    public void exampleTest() {
    }

}
