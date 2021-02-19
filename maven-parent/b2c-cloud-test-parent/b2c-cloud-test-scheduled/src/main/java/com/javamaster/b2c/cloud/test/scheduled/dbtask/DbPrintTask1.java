package com.javamaster.b2c.cloud.test.scheduled.dbtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author yudong
 * @date 2019/5/10
 */
@Component
public class DbPrintTask1 implements ScheduledTask {

    Logger logger = LoggerFactory.getLogger(getClass());

    private int i;

    @Override
    public void execute() {
        logger.info("dbPrintTask1 execute times:{}", ++i);
    }

}
