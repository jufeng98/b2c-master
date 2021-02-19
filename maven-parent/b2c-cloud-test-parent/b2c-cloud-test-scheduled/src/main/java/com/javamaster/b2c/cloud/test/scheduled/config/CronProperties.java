package com.javamaster.b2c.cloud.test.scheduled.config;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * @author yudong
 * @date 2019/5/10
 */
@Component
@ManagedResource(objectName = "scheduled:mbean=CronProperties")
public class CronProperties {

    private String cron = "* * 3 * * ?";

    @ManagedAttribute
    public String getCron() {
        return cron;
    }

    @ManagedAttribute
    public void setCron(String cron) {
        this.cron = cron;
    }
}
