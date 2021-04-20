package org.javamaster.b2c.spring.aop;

import org.javamaster.b2c.spring.aop.service.AopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yudong
 * @date 2021/4/20
 */
@SpringBootTest(classes = SpringAopApplication.class)
public class AopTest {

    @Autowired
    private AopService aopService;

    @Test
    public void test() {
        System.out.println(aopService.getSimpleName());
    }

}
