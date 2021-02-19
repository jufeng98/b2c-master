package com.javamaster.b2c.cloud.test.scheduled.dbtask;

import com.javamaster.b2c.cloud.test.scheduled.config.SchuduledCronProperties;
import com.javamaster.b2c.cloud.test.scheduled.entity.SpringSchuduledCron;
import com.javamaster.b2c.cloud.test.scheduled.util.SpringUtils;

/**
 * @author yudong
 * @date 2019/5/11
 */
public interface ScheduledTask extends Runnable {

    void execute();

    @Override
    default void run() {
        SchuduledCronProperties properties = SpringUtils.getBean(SchuduledCronProperties.class);
        SpringSchuduledCron schuduledCron = properties.findByCronKey(this.getClass().getName());
        if (schuduledCron.getStatus() == 1) {
            return;
        }
        execute();
    }
}
