package com.javamaster.b2c.cloud.test.learn.java.java8;

import static java.util.stream.Collectors.partitioningBy;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created on 2018/12/24.<br/>
 *
 * @author yudong
 */
public class PrimesTest {

    @Test
    public void test() {
        System.out.println(new PrimesTest().partitionPrimes(20));
    }

    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(candidate -> isPrime(candidate)));
    }

    public boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

}
