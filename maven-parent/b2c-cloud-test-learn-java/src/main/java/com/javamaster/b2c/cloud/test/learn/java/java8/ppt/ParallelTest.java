package com.javamaster.b2c.cloud.test.learn.java.java8.ppt;

import static java.util.stream.Collectors.toList;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author yu
 */
public class ParallelTest {

    @Test
    public void test() {
        List<Transaction> transactions = IntStream.range(0, 80)
                .mapToObj(i -> {
                    Transaction transaction = new Transaction();
                    transaction.setYear(RandomUtils.nextInt());
                    transaction.setMonth(RandomUtils.nextInt(0, 13));
                    transaction.setValue(RandomUtils.nextInt());
                    return transaction;
                })
                .collect(toList());
        long start = System.currentTimeMillis();
        transactions.stream()
                .filter(transaction -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return RandomUtils.nextBoolean();
                })
                .collect(Collectors.toList());
        System.out.println(System.currentTimeMillis() - start + "ms");
        start = System.currentTimeMillis();
        transactions.parallelStream()
                .filter(transaction -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return RandomUtils.nextBoolean();
                })
                .collect(Collectors.toList());
        System.out.println(System.currentTimeMillis() - start + "ms");
    }

    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        System.out.println(sideEffectSum(10_000_000L));
        System.out.println(System.currentTimeMillis() - start + "ms");

        start = System.currentTimeMillis();
        System.out.println(sideEffectSumParallel(10_000_000L));
        System.out.println(System.currentTimeMillis() - start + "ms");
    }

    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public static long sideEffectSumParallel(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public static class Accumulator {
        public long total = 0;

        public void add(long value) {
            total += value;
        }
    }
}
