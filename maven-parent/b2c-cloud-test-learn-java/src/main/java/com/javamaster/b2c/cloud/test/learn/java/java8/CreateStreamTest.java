package com.javamaster.b2c.cloud.test.learn.java.java8;

import org.springframework.util.ResourceUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created on 2018/12/19.<br/>
 *
 * @author yudong
 */
public class CreateStreamTest {
    public static void main(String[] args) throws Exception {
        Stream<String> stringStream = Stream.of("hello", " ", "world");
        System.out.println(stringStream.map(String::toUpperCase).collect(Collectors.joining()));

        int[] numbers = {1, 2, 4, 5, 6, 8};
        IntStream intStream = Arrays.stream(numbers);
        System.out.println(intStream.sum());

        Stream<String> fileStream = Files.lines(ResourceUtils.getFile("classpath:mybatis-config.xml").toPath());
        System.out.println(fileStream.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count());

        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
        System.out.println("---");

        Stream.iterate(new int[]{0, 1}, (int[] ints) -> {
            int temp = ints[1];
            ints[1] = ints[0] + ints[1];
            ints[0] = temp;
            return ints;
        }).limit(20).forEach(ints -> System.out.println(Arrays.toString(ints)));
        System.out.println("---");

        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
        System.out.println("---");

        // 有状态的操作
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}
