package com.javamaster.b2c.cloud.test.pattern.effectivejava.multithread;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created on 2018/8/15.<br/>
 *
 * @author yudong
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws Exception {

        CountDownLatch start = new CountDownLatch(1);

        CountDownLatch done = new CountDownLatch(2);

        Player p1 = new Player(start, done);
        Player p2 = new Player(start, done);

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(p1);
        service.execute(p2);
        service.shutdown();

        start.countDown();
        long startTime = System.currentTimeMillis();
        System.out.println("发令枪响时间:" + new Date(startTime));

        done.await();
        long endTime = System.currentTimeMillis();
        System.out.println("所有运动员均已到终点的时间:" + new Date(endTime));
        System.out.println("平均成绩为:" + (endTime - startTime) / 2 + "ms");

    }
}
