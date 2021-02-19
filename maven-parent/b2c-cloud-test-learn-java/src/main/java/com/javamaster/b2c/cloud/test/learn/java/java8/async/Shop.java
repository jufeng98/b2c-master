package com.javamaster.b2c.cloud.test.learn.java.java8.async;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author yudong
 * @date 2019/1/8
 */
public class Shop {

    private String name;
    static Random random = new Random();

    public Shop(String name) {
        this.name = name;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public String getPriceSec(String product) {
        return calculatePriceSec(product);
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception ex) {
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        return futurePrice;
    }

    public Future<Double> getPriceAsync1(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        if ("my product".equals(product)) {
            throw new IllegalArgumentException("product not available");
        }
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private String calculatePriceSec(String product) {
        delay();
        if ("my product".equals(product)) {
            throw new IllegalArgumentException("product not available");
        }
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    public static void delay() {
        try {
            TimeUnit.MILLISECONDS.sleep(500 + random.nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name;
    }
}
