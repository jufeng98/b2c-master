package com.javamaster.b2c.cloud.test.learn.java.multithread;

import org.junit.Test;

import java.io.*;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author yu
 */
public class BankTest {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        Bank bank = new Bank();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                while (true) {
                    Random random = new Random();
                    int from = random.nextInt(10);
                    int to = random.nextInt(10);
                    int amount = random.nextInt(200) + 100;
                    bank.transfer(from, to, amount);
                }
            });
        }
    }

    @Test
    public void test22() throws Exception {
        PipedWriter pipedWriter = new PipedWriter();
        PipedReader pipedReader = new PipedReader();
        pipedWriter.connect(pipedReader);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            try {
                pipedWriter.write(1);
                TimeUnit.SECONDS.sleep(3);
                pipedWriter.write(2);
                TimeUnit.SECONDS.sleep(3);
                pipedWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.submit(()->{
            try {
                while (true) {
                    if (pipedReader.ready()) {
                        int read = pipedReader.read();
                        System.out.println(read);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        TimeUnit.SECONDS.sleep(10);
    }
}
