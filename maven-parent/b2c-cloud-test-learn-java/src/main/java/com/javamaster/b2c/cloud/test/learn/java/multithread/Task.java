package com.javamaster.b2c.cloud.test.learn.java.multithread;

/**
 * @author yudong
 * @date 2021/6/14
 */
public class Task implements Runnable {
    final Object object = new Object();
    @Override
    public void run() {
        synchronized (object) {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread " + Thread.currentThread().getName() + " " + i);
            }
        }
    }
}
