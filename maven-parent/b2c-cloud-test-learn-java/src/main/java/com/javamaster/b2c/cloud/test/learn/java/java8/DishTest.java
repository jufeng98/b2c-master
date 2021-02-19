package com.javamaster.b2c.cloud.test.learn.java.java8;

import com.javamaster.b2c.cloud.test.learn.java.java8.model.Dish;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created on 2018/12/24.<br/>
 *
 * @author yudong
 */
public class DishTest {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
        List<String> threeHighCaloricDishNames = menu
                .stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());
        System.out.println(threeHighCaloricDishNames);
        System.out.println("---");

        List<String> names =
                menu.stream()
                        .filter(d -> {
                            System.out.println("filtering" + d.getName());
                            return d.getCalories() > 300;
                        })
                        .map(d -> {
                            System.out.println("mapping" + d.getName());
                            return d.getName();
                        })
                        .limit(3)
                        .collect(toList());
        System.out.println(names);
        System.out.println("---");

        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());
        System.out.println(vegetarianMenu);
        System.out.println("---");


        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
        System.out.println("---");

        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList());
        System.out.println(dishes);
        System.out.println("---");

        dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());
        System.out.println(dishes);
        System.out.println("---");

        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        System.out.println(dishNameLengths);
        System.out.println("---");

        String[] strings = new String[]{"hello", "world"};
        System.out.println(Arrays.stream(strings).map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList()));
        System.out.println("---");

        Integer[] integers = new Integer[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.stream(integers).map(integer -> integer * integer).collect(toList()));
        System.out.println("---");

        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        if (!menu.stream().allMatch(Dish::isVegetarian)) {
            System.out.println("The menu is not all vegetarian!!");
        }

        if (menu.stream().noneMatch(d -> d.getCalories() >= 1000)) {
            System.out.println("The menu is lower 1000");
        }

        Optional<Dish> optionalDish = menu.stream().filter(Dish::isVegetarian).findAny();
        optionalDish.ifPresent(dish -> System.out.println(dish));

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(x -> x * x)
                        .filter(x -> x % 3 == 0)
                        .findFirst();
        firstSquareDivisibleByThree.ifPresent(integer -> System.out.println(integer));
        System.out.println("---");

        System.out.println(someNumbers.stream().reduce(1, (a, b) -> a * b));
        System.out.println(someNumbers.stream().reduce(0, Integer::sum));
        System.out.println(someNumbers.stream().reduce(Integer::max));
        System.out.println(menu.stream().map(dish -> 1).reduce(0, Integer::sum));
        System.out.println(menu.stream().count());
        System.out.println("---");

        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        System.out.println(intStream.sum());
        intStream = menu.stream().mapToInt(Dish::getCalories);
        System.out.println(intStream.max());
        Stream<Integer> stream = menu.stream().mapToInt(Dish::getCalories).boxed();
        System.out.println(stream.reduce((a, b) -> a + b));
        System.out.println("---");

        IntStream numbersStream = IntStream.rangeClosed(1, 100).filter(i -> i % 2 == 0);
        System.out.println(numbersStream.count());

        System.out.println("---");
        int a = 3;
        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(ints -> System.out.println(Arrays.toString(ints)));
        System.out.println("---");

        Stream<double[]> pythagoreanTriples2 =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(g -> IntStream
                                .rangeClosed(g, 100)
                                .mapToObj(b -> new double[]{g, b, Math.sqrt(a * a + b * b)})
                                .filter(t -> t[2] % 1 == 0));
        pythagoreanTriples2.forEach(trians -> System.out.println(Arrays.toString(trians)));

        // 第一种方式
        dishes = menu.stream().collect(new ToListCollector<>());
        // 第二种方式
        dishes = menu.stream().collect(
                ArrayList::new,
                List::add,
                List::addAll);
        System.out.println(dishes);
        System.out.println("---");
    }
}
