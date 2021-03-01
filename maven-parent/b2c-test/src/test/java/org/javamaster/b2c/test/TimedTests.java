package org.javamaster.b2c.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
public class TimedTests {

    @Test
    @Timed(millis = 1000)
    public void test() {
        // try {
        //     TimeUnit.MILLISECONDS.sleep(1200);
        // } catch (InterruptedException ignored) {
        // }
        log.info("execute");
    }

}
