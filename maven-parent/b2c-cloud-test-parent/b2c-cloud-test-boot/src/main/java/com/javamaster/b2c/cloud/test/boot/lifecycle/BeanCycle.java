package com.javamaster.b2c.cloud.test.boot.lifecycle;

import org.springframework.context.SmartLifecycle;

/**
 * @author yudong
 * @date 2022/4/10
 */
public class BeanCycle implements SmartLifecycle {
    @Override
    public void start() {
        System.out.println("start");
    }

    @Override
    public void stop() {
        System.out.println("stop");
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
