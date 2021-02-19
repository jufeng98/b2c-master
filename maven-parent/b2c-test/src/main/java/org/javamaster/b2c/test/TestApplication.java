package org.javamaster.b2c.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author yudong
 * @date 2019/12/19
 */
@SpringBootApplication
public class TestApplication {

    private static ApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(TestApplication.class, args);
    }

    public static ApplicationContext getContext() {
        return context;
    }

}
