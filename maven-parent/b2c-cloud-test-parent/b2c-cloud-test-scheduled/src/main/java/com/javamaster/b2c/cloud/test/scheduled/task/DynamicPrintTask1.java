package com.javamaster.b2c.cloud.test.scheduled.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author yudong
 * @date 2019/5/10
 */
@Component("dynamicPrintTask1")
public class DynamicPrintTask1 implements Runnable {
    Logger logger = LoggerFactory.getLogger(getClass());
    private int i;

    public void execute() {
        logger.info("dynamic1 execute times:{}", ++i);
    }

    @Override
    public void run() {
        execute();
    }
}
