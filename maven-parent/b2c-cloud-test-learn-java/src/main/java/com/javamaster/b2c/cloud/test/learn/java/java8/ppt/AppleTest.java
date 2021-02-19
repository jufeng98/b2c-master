package com.javamaster.b2c.cloud.test.learn.java.java8.ppt;

import static java.util.stream.Collectors.groupingBy;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yu
 */
public class AppleTest {
    @Test
    public void test() {
        List<Apple> inventory = Arrays.asList(
                new Apple("green", 300, true), new Apple("yellow", 800, false),
                new Apple("red", 100, false), new Apple("yellow", 500, true)
        );
        Map<String, List<AppleVo>> map = inventory.stream()
                .filter(apple -> apple.getWeight() > 150)
                .sorted(Comparator.comparing(Apple::isGood).thenComparing(Apple::getWeight))
                .map(apple -> {
                    AppleVo appleVo = new AppleVo();
                    BeanUtils.copyProperties(apple, appleVo);
                    return appleVo;
                })
                .collect(groupingBy(appleVo -> appleVo.getColor()));
        System.out.println(map);

        List<Apple> heavyApples = filterApples(inventory, (Apple apple) -> apple.getWeight() > 150);
        System.out.println(heavyApples);
        heavyApples = filterApples(inventory, apple -> apple.getWeight() > 150);
        System.out.println(heavyApples);

        inventory.stream().filter(apple -> apple.isGood());
        inventory.stream().filter(Apple::isGood);

        System.out.println(inventory.stream().sorted(Comparator.comparing(Apple::getWeight)).collect(Collectors.toList()));
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
