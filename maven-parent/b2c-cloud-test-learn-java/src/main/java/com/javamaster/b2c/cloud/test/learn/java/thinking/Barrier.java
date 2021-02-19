package com.javamaster.b2c.cloud.test.learn.java.thinking;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yu
 */
public class Barrier implements Runnable {
    private CyclicBarrier barrier;
    private Random random = new Random();
    private static int num = 1;
    private int id = num++;

    public Barrier(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(id + " working......");
            TimeUnit.MILLISECONDS.sleep(random.nextInt(5000));
            System.out.println(id + " Working done.Enter barrier and wait");
            barrier.await();
            System.out.println(id + " Jump out barrier");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}

class BarrierTest {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("all thread enter info barrier"));
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            service.execute(new Barrier(barrier));
        }
        service.shutdown();
    }
}
