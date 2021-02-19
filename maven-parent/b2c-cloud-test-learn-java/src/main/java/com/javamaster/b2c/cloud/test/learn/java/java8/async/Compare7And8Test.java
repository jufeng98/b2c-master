package com.javamaster.b2c.cloud.test.learn.java.java8.async;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created on 2019/1/8.<br/>
 *
 * @author yudong
 */
public class Compare7And8Test {

    static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"));

        long start = System.nanoTime();
        System.out.println(findPricesSequence("soap", shops));
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation sequence after all shop " + invocationTime + " msecs");

        start = System.nanoTime();
        System.out.println(findPricesAsync7("soap", shops));
        invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation java7 after all shop " + invocationTime + " msecs");

        start = System.nanoTime();
        System.out.println(findPricesAsync8("soap", shops));
        invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation java8 after all shop " + invocationTime + " msecs");

        start = System.nanoTime();
        System.out.println(findPricesAsyncOther8("soap", shops));
        invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation other java8 after all shop " + invocationTime + " msecs");

        start = System.nanoTime();
        findAnyPricesAsyncOther8("soap", shops);
        invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation any other java8 after all shop " + invocationTime + " msecs");

        start = System.nanoTime();
        CompletableFuture.allOf(
                findPricesStream("soap", shops).map(f -> f.thenAccept(System.out::println)).toArray(size -> new CompletableFuture[size])
        ).join();
        invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation find other java8 after all shop " + invocationTime + " msecs");

        executor.shutdown();
    }

    public static List<String> findPricesSequence(String product, List<Shop> shops) {
        List<String> list = new ArrayList<>(shops.size());
        for (Shop shop : shops) {
            String s = shop.getPriceSec(product);
            Quote quote = Quote.parse(s);
            double rate = RateUtils.getRete("USA", "EU");
            Quote euQuote = new Quote(quote.getShopName(), rate * quote.getPrice(), quote.getDiscountCode());
            String res = Discount.applyDiscount(euQuote);
            list.add(res);
        }
        return list;
    }

    public static List<String> findPricesAsync7(String product, List<Shop> shops) throws Exception {
        List<String> list = new ArrayList<>(shops.size());
        List<Future<String>> futures = new ArrayList<>(shops.size());
        for (Shop shop : shops) {

            Future<String> future = executor.submit(() -> shop.getPriceSec(product));

            Future<Double> quoteFuture = executor.submit(() -> RateUtils.getRete("USA", "EU"));

            Future<String> stringFuture = executor.submit(() -> {
                Double rate = quoteFuture.get();
                Quote quote = Quote.parse(future.get());
                Quote euQuote = new Quote(quote.getShopName(), rate * quote.getPrice(), quote.getDiscountCode());
                return Discount.applyDiscount(euQuote);
            });
            futures.add(stringFuture);
        }

        for (Future<String> future : futures) {
            list.add(future.get());
        }
        return list;
    }

    public static List<String> findPricesAsync8(String product, List<Shop> shops) {
        return shops.parallelStream()
                .map(shop -> shop.getPriceSec(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    public static List<String> findPricesAsyncOther8(String product, List<Shop> shops) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceSec(product))
                        .thenCombine(CompletableFuture.supplyAsync(() -> RateUtils.getRete("USA", "EU")), (string, rate) -> {
                            Quote quote = Quote.parse(string);
                            Quote euQuote = new Quote(quote.getShopName(), rate * quote.getPrice(), quote.getDiscountCode());
                            return euQuote;
                        }))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    public static void findAnyPricesAsyncOther8(String product, List<Shop> shops) throws Exception {
        CompletableFuture[] futures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceSec(product), executor)
                        .thenCombine(CompletableFuture.supplyAsync(() -> RateUtils.getRete("USA", "EU"), executor), (string, rate) -> {
                            Quote quote = Quote.parse(string);
                            Quote euQuote = new Quote(quote.getShopName(), rate * quote.getPrice(), quote.getDiscountCode());
                            return euQuote;
                        }))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .map(future -> future.thenAccept(s -> System.out.println(s)))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();

    }

    public static Stream<CompletableFuture<String>> findPricesStream(String product, List<Shop> shops) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceSec(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
    }
}
