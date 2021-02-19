package com.javamaster.b2c.cloud.test.learn.java.java8.librarychange;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

/**
 * Created on 2019/5/7.
 *
 * @author yudong
 */
public class ChangeTest {

    @Test
    public void mapTest() {
        Map<String, String> map = Maps.newHashMap();
        map.put("hello", "world");
        map.put("yu", "dong");
        map.put("love", "you");

        System.out.println(map.getOrDefault("you", "beauty"));

        map.compute("yu", (key, value) -> {
            String firstName = "liang";
            return firstName + " " + key + value;
        });

        map.computeIfAbsent("company", key -> {
            String city = "GuangZhou";
            return key + " " + city;
        });

        map.putIfAbsent("name", "liangyudong");

        map.merge("noneExists", "newValue", (oldValue, value) -> String.join(",", oldValue, value));
        map.merge("hello", "reallyGood", (oldValue, value) -> String.join(",", oldValue, value));

        map.remove("love", "you");

        map.replace("name", "Liang Yudong");

        map.replaceAll((key, value) -> key.toUpperCase() + value.toUpperCase());

        map.forEach((key, value) -> System.out.println(String.join(" ", key, value)));
    }

    @Test
    public void listTest() {
        List<String> list = Lists.newArrayList(Arrays.asList("hello", "world", "good"));

        list.forEach(System.out::println);

        Spliterator<String> stringSpliterator = list.spliterator();
        System.out.println(stringSpliterator.estimateSize());

        Iterator<String> iterator = list.iterator();
        iterator.forEachRemaining(System.out::println);

        list.removeIf(s -> s.contains("g"));

        list.replaceAll(String::toUpperCase);

        list.sort(Comparator.naturalOrder());

        list.forEach(System.out::println);
    }

    AtomicInteger atomicInteger = new AtomicInteger(0);

    @Test
    public void atomicTest() {
        System.out.println(atomicInteger.getAndUpdate(i -> ++i));
        System.out.println(atomicInteger.updateAndGet(i -> ++i));

        System.out.println(atomicInteger.getAndAccumulate(3, (i, j) -> i + j));
        System.out.println(atomicInteger.accumulateAndGet(3, (i, j) -> i + j));
        int min = atomicInteger.accumulateAndGet(6, Integer::min);
        System.out.println(min);

        // 多个线程需要频繁地进行更新操作，且很少有读取的动作
        LongAdder adder = new LongAdder();
        IntStream.range(1, 20).forEach(i -> {
            adder.add(i);
        });
        System.out.println(adder.sum());

        LongAccumulator acc = new LongAccumulator(Long::sum, 0);
        IntStream.range(1, 20).forEach(i -> {
            acc.accumulate(i);
        });
        System.out.println(acc.get());

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>(10);
        map.put("a", 20);
        map.put("b", 25);
        map.put("c", 30);
        System.out.println(map.size());

        System.out.println(map.mappingCount());

        Optional<Integer> maxValue = Optional.of(map.reduceValues(1, Integer::max));
        System.out.println(maxValue);

        map.keySet().forEach(key -> System.out.println(key));
    }

    @Test
    public void arraysTest() {
        int[] numbers = new int[]{2, 54, 33, 23, 99, 88};
        Arrays.parallelSort(numbers);
        System.out.println(Arrays.toString(numbers));

        Arrays.setAll(numbers, index -> index * 2);
        System.out.println(Arrays.toString(numbers));

        int[] ones = new int[10];
        Arrays.fill(ones, 1);
        System.out.println(Arrays.toString(ones));
        Arrays.parallelPrefix(ones, (lastAccumulate, one) -> lastAccumulate + one);
        System.out.println(Arrays.toString(ones));
    }

    @Test
    public void numberTest() {
        System.out.println(Integer.sum(23, 33));
        System.out.println(Double.sum(23.33, 33.22));
        System.out.println(Integer.max(23, 33));
        System.out.println(Integer.min(23, 33));
        System.out.println(Integer.compareUnsigned(241223, 2343));
        System.out.println(Integer.parseUnsignedInt("24"));
        System.out.println(Integer.toUnsignedLong('3'));
        System.out.println(Double.isFinite(Double.POSITIVE_INFINITY));

        boolean a = false;
        boolean b = true;
        System.out.println(Boolean.logicalAnd(a, b));
        System.out.println(Boolean.logicalOr(a, b));
        System.out.println(Boolean.logicalXor(a, b));

        BigInteger bigInteger = new BigInteger("113");
        System.out.println(bigInteger.byteValueExact());
        System.out.println(bigInteger.shortValueExact());

        System.out.println(Math.addExact(23, 33));
        System.out.println(Math.incrementExact(Integer.MAX_VALUE));
    }

    @Test
    public void filesTest() throws Exception {
        Files.list(Paths.get("G:")).forEach(System.out::println);
        System.out.println("---");

        try {
            Files.walk(Paths.get("G:"), 2).forEach(System.out::println);
            System.out.println("---");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Files.find(Paths.get("G:"), 2, (path, attributes) -> {
            if (path.toString().contains("b2c-cloud")) {
                return true;
            }
            return false;
        }).forEach(System.out::println);
    }
}
