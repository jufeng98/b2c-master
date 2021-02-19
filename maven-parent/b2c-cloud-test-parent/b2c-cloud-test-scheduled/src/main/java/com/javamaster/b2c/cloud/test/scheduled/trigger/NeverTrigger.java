package com.javamaster.b2c.cloud.test.scheduled.trigger;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;

import java.util.Calendar;
import java.util.Date;

/**
 * @author yudong
 * @date 2019/5/11
 */
public class NeverTrigger implements Trigger {
    @Override
    public Date nextExecutionTime(TriggerContext triggerContext) {
        return new Calendar.Builder().setDate(2099, Calendar.JANUARY, 1).build().getTime();
    }
}
