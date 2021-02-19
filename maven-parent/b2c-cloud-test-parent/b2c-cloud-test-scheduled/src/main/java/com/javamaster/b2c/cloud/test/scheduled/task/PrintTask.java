package com.javamaster.b2c.cloud.test.scheduled.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yudong
 * @date 2019/5/10
 */
@Component
public class PrintTask {
    Logger logger = LoggerFactory.getLogger(getClass());
    private int i;

    @Scheduled(cron = "* * 1 * * ?")
    public void execute() {
        logger.info("execute times:{}", ++i);
    }

}
