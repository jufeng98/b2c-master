package com.javamaster.b2c.cloud.test.pattern.effectivejava.multithread;


import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2018/8/15.<br/>
 *
 * @author yudong
 */
public class PlayerCyc implements Runnable {

    private CyclicBarrier start;
    private CyclicBarrier end;

    public PlayerCyc(CyclicBarrier start, CyclicBarrier end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        try {

            start.await();

            int sec = RandomUtils.nextInt(10, 15);
            TimeUnit.SECONDS.sleep(sec);

            // 跑到终点
            end.await();

        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}
