package com.javamaster.b2c.cloud.test.scheduled.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yudong
 * @date 2019/5/10
 */
public class DynamicPrintTask implements Runnable {
    Logger logger = LoggerFactory.getLogger(getClass());
    private int i;

    public void execute() {
        logger.info("dynamic execute times:{}", ++i);
    }

    @Override
    public void run() {
        execute();
    }
}
