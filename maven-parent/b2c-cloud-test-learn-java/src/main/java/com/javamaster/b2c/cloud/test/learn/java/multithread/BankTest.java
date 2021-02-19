package com.javamaster.b2c.cloud.test.learn.java.multithread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
}
