package org.javamaster.b2c.test.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yudong
 * @date 2021/2/24
 */
@SuppressWarnings("NullableProblems")
public class SetEnvApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        // 修改这里实现使用不同的环境配置
        // test,prd
        System.setProperty("env", "test");
    }

}
