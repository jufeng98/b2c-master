package com.javamaster.b2c.cloud.test.pattern.effectivejava.multithread;

import java.util.concurrent.TimeUnit;

/**
 * Created on 2018/8/10.<br/>
 *
 * @author yudong
 */
public class StopThread {

    private static boolean stopRequested;

    private static volatile int nextNum;

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
            }
            System.out.println("loop end:" + i);
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;

        System.out.println(getNextNum());
    }

    public static int getNextNum() {
        return nextNum++;
    }

}
