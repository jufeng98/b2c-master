package org.javamaster.b2c.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@IfProfileValue(name = "sun.desktop", value = "windows")
public class IfProfileValueTests {

    @Test
    public void test() {
        log.info("execute");
    }

    @Test
    @IfProfileValue(name = "java.vendor", value = "Oracle Corporation")
    public void exampleTest() {
        log.info("execute");
    }

}
