package org.javamaster.spring.test.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author yudong
 * @date 2021/5/18
 */
public class ContextTestUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext ac) {
        ContextTestUtils.applicationContext = ac;
    }

    public static ApplicationContext getContext() {
        return applicationContext;
    }
}
