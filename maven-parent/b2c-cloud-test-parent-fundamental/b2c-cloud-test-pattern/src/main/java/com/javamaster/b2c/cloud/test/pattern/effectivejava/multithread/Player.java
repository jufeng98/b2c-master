package com.javamaster.b2c.cloud.test.pattern.effectivejava.multithread;


import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2018/8/15.<br/>
 *
 * @author yudong
 */
public class Player implements Runnable {

    private CountDownLatch start;
    private CountDownLatch done;

    public Player(CountDownLatch start, CountDownLatch done) {
        this.start = start;
        this.done = done;
    }

    @Override
    public void run() {
        try {
            // 等待发令枪响
            start.await();

            int sec = RandomUtils.nextInt(10, 15);
            TimeUnit.SECONDS.sleep(sec);

            // 跑到终点
            done.countDown();

        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}
