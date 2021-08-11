package com.javamaster.b2c.cloud.test.learn.java.multithread;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ThreadTest {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object obj = new Object();
        System.out.println(obj.toString());
        createLockThread(obj);
        br.readLine();
        new Thread(new SynAddRunnable(1, 2), "testDeadLockThread1").start();
        new Thread(new SynAddRunnable(2, 1), "testDeadLockThread2").start();
    }

    /**
     * 线程死循环演示
     */
    public static void createBusyThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                }
            }
        }, "testBusyThread");
        thread.start();
    }

    /**
     * 线程锁等待演示
     */
    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");
        thread.start();
    }

    /**
     * 线程死锁等待演示
     */
    static class SynAddRunnable implements Runnable {
        Integer a, b;

        public SynAddRunnable(int a, int b) {
            this.a = a;
            this.b = b;
            System.out.println("lock a:" + this.a);
            System.out.println("lock b:" + this.b);
        }

        @Override
        public void run() {
            synchronized (a) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + " " + (a + b));
                }
            }
        }
    }
}