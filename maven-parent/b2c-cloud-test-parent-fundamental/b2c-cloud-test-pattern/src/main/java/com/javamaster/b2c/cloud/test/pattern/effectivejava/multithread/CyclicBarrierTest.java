package com.javamaster.b2c.cloud.test.pattern.effectivejava.multithread;

import java.util.Date;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created on 2018/8/15.<br/>
 *
 * @author yudong
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws Exception {

        CyclicBarrier start = new CyclicBarrier(3);

        final long[] startTime = new long[1];

        CyclicBarrier end = new CyclicBarrier(2, () -> {
            long endTime = System.currentTimeMillis();
            System.out.println("所有运动员均已到终点的时间:" + new Date(endTime));
            System.out.println("平均成绩为:" + (endTime - startTime[0]) + "ms");
        });

        PlayerCyc playerCyc1 = new PlayerCyc(start, end);
        PlayerCyc playerCyc2 = new PlayerCyc(start, end);

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(playerCyc1);
        service.execute(playerCyc2);
        service.shutdown();

        start.await();
        startTime[0] = System.currentTimeMillis();
        System.out.println("发令枪响时间:" + new Date(startTime[0]));

    }
}
