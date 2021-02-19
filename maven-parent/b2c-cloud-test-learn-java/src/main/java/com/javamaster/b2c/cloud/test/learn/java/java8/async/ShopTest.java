package com.javamaster.b2c.cloud.test.learn.java.java8.async;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created on 2019/1/8.<br/>
 *
 * @author yudong
 */
public class ShopTest {
    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync1("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");
        // 执行更多任务，比如查询其他商店
        // doSomethingElse();
        // 在计算商品价格的同时
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");


        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"));

        start = System.nanoTime();
        System.out.println(findPrices("soap", shops));
        invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after all shop " + invocationTime + " msecs");

        start = System.nanoTime();
        System.out.println(findPricesAsync("soap", shops));
        invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation async returned after all shop " + invocationTime + " msecs");

        start = System.nanoTime();
        System.out.println(findPricesAsync1("soap", shops));
        invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation async future returned after all shop " + invocationTime + " msecs");

        start = System.nanoTime();
        System.out.println(findPricesSec("soap", shops));
        invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation sec returned after all shop " + invocationTime + " msecs");

        start = System.nanoTime();
        System.out.println(findPricesSecAsync("soap", shops));
        invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation sec async returned after all shop " + invocationTime + " msecs");
    }

    public static List<String> findPrices(String product, List<Shop> shops) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(toList());
    }

    public static List<String> findPricesAsync(String product, List<Shop> shops) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(toList());
    }

    public static List<String> findPricesAsync1(String product, List<Shop> shops) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))))
                        .collect(toList());
        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public static List<String> findPricesSec(String product, List<Shop> shops) {
        List<String> strings = shops.stream()
                .map(shop -> shop.getPriceSec(product))
                .map(Quote::parse).map(Discount::applyDiscount).collect(toList());
        return strings;
    }

    public static List<String> findPricesSecAsync(String product, List<Shop> shops) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceSec(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }
}
