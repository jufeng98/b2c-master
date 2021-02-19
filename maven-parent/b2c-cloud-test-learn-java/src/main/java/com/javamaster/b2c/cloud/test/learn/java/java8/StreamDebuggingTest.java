package com.javamaster.b2c.cloud.test.learn.java.java8;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2019/1/7.<br/>
 *
 * @author yudong
 */
public class StreamDebuggingTest {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
        numbers.stream()
                .map(x -> x + 17)
                .peek(x -> System.out.println("after map:" + x))
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("after filter:" + x))
                .limit(3)
                .peek(x -> System.out.println("after limit:" + x))
                .forEach(System.out::println);

        List<Point> points = Arrays.asList(new Point(12, 2), null);
        points.stream().map(Point::getX).forEach(System.out::println);
    }
}
