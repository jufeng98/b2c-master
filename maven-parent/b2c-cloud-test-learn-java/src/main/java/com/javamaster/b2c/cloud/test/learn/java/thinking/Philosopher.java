package com.javamaster.b2c.cloud.test.learn.java.thinking;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yu
 */
public class Philosopher implements Runnable {
    private ThreadLocal<Random> threadLocal = new ThreadLocal<Random>() {
        @Override
        protected Random initialValue() {
            return new Random();
        }
    };
    private Chopstick[] chopsticks;
    private int left;
    private int right;

    public final int ID = (int) (Math.random() * 10000);

    public Philosopher(Chopstick[] chopsticks, int left, int right) {
        this.chopsticks = chopsticks;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(ID + " thinking.....");
                TimeUnit.MICROSECONDS.sleep(threadLocal.get().nextInt(300));
                chopsticks[right].take();
                System.out.println(ID + " get right chopstick");
                chopsticks[left].take();
                System.out.println(ID + " get left chopstick");
                System.out.println(ID + " eating......");
                TimeUnit.MICROSECONDS.sleep(threadLocal.get().nextInt(300));
                chopsticks[left].drop();
                chopsticks[right].drop();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class PhilosopherTest {
    public static void main(String[] args) {
        Chopstick[] chopsticks = new Chopstick[5];
        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Chopstick();
        }
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < chopsticks.length; i++) {
            // 取消注释即可避免死锁
            // if (i == 4) {
            // service.compileAndExecuteSourceCode(new Philosopher(chopsticks, (i + 1) % 5, i));
            // continue;
            // }
            service.execute(new Philosopher(chopsticks, i, (i + 1) % 5));
        }
        service.shutdown();
    }
}
