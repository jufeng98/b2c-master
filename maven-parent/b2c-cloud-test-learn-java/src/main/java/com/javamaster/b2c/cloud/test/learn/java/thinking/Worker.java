package com.javamaster.b2c.cloud.test.learn.java.thinking;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yu
 */
public class Worker implements Runnable {
    private CountDownLatch latch;
    private Random random = new Random();
    private static int num = 1;
    private int id = num++;

    public Worker(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(id + " start to working");
            TimeUnit.MILLISECONDS.sleep(random.nextInt(5000));
            System.out.println(id + " working complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            latch.countDown();
        }
    }

}

class WorkerTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            service.execute(new Worker(latch));
        }
        service.shutdown();
        latch.await();
        System.out.println("all works done");
    }
}