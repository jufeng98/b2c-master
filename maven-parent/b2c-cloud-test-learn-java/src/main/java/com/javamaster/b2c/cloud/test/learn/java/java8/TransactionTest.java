package com.javamaster.b2c.cloud.test.learn.java.java8;

import com.alibaba.fastjson.JSONObject;
import com.javamaster.b2c.cloud.test.learn.java.java8.model.PriceLevel;
import com.javamaster.b2c.cloud.test.learn.java.java8.model.Trader;
import com.javamaster.b2c.cloud.test.learn.java.java8.model.Transaction;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created on 2018/12/19.<br/>
 *
 * @author yudong
 */
public class TransactionTest {

    @Test
    public void test2() {
        List<Transaction> transactions = transactionList();
        System.out.println(
                transactions.stream()
                        .collect(partitioningBy(transaction -> transaction.getValue() > 800, partitioningBy(transaction -> transaction.getYear() > 2011)))
        );
        System.out.println("---");
        System.out.println(transactions.stream().collect(partitioningBy(transaction -> transaction.getValue() > 800)));
        System.out.println("---");
        System.out.println(transactions.stream().collect(groupingBy(transaction -> transaction.getCurrency(),
                mapping(Transaction::priceLevel, toSet()))));
    }

    @Test
    public void test3() {
        List<Transaction> transactions = transactionList();
        System.out.println(transactions.stream().collect(groupingBy(transaction -> transaction.getCurrency(),
                summingInt(Transaction::getValue))));
        System.out.println("---");
        System.out.println(transactions.stream().collect(groupingBy(transaction -> transaction.getCurrency(),
                maxBy((t1, t2) -> Integer.max(t1.getValue(), t2.getValue())))));
        System.out.println("---");
        System.out.println(transactions.stream().collect(groupingBy(transaction -> transaction.getCurrency(),
                collectingAndThen(maxBy((t1, t2) -> Integer.max(t1.getValue(), t2.getValue())), Optional::get))));
    }

    @Test
    public void test4() {
        List<Transaction> transactions = transactionList();
        System.out.println(transactions.stream().collect(groupingBy(transaction -> transaction.getTrader().getName())));
        System.out.println(transactions.stream().collect(groupingBy(transaction -> transaction.getTrader().getName(), toList())));
        System.out.println(transactions.stream().collect(groupingBy(transaction -> transaction.getTrader().getName(), counting())));
        System.out.println(filter(transactions, transaction -> transaction.getYear() == 2011));
    }

    @Test
    public void test5() {
        List<Transaction> transactions = transactionList();
        // 方法引用
        transactions.stream().sorted((t1, t2) -> {
            return Integer.compare(t1.getYear(), t2.getYear());
        });
        transactions.stream().sorted((t1, t2) -> Integer.compare(t1.getYear(), t2.getYear()));
        transactions.stream().sorted(Comparator.comparingInt(Transaction::getYear));
        transactions.stream().forEach(transaction -> System.out.println(transaction));
        transactions.stream().forEach(System.out::println);
        Stream.of("hello", " world").map(String::toUpperCase).forEach(System.out::println);
        Supplier<Trader> supplier = Trader::new;
        System.out.println(supplier.get());
        BiFunction<String, String, Trader> biFunction = Trader::new;
        System.out.println(biFunction.apply("Tom", "Tokyo"));
    }

    @Test
    public void test6() {
        List<Transaction> transactions = transactionList();
        System.out.println(transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().collect(Collectors.joining(",")));
        Stream.of(1, 2, 2, 5, 3, 2).filter(i -> i < 6).distinct().forEach(System.out::print);
        System.out.println(Stream.of("hello", "world").map(word -> word.split("")).distinct().collect(toList()));
        System.out.println(Stream.of("hello", "world").map(word -> word.split("")).map(Arrays::stream).distinct().collect(toList()));
        System.out.println(Stream.of("hello", "world").map(word -> word.split("")).flatMap(Arrays::stream).distinct().collect(toList()));
    }

    @Test
    public void test7() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream().flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j})).collect(toList());
        System.out.println(JSONObject.toJSONString(pairs));
    }

    @Test
    public void test8() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream().map(n -> n * n).collect(toList());
        System.out.println(squares);
        System.out.println(numbers.stream().filter(integer -> integer % 2 == 0).findAny());
        System.out.println(numbers.stream().filter(integer -> integer % 2 == 0).findFirst());
        System.out.println(numbers.stream().anyMatch(integer -> integer % 2 == 0));
        System.out.println(numbers.stream().allMatch(integer -> integer % 2 == 0));
        System.out.println(numbers.stream().noneMatch(integer -> integer % 2 == 0));
        System.out.println(numbers.stream().reduce(0, (a, b) -> a + b));
        System.out.println(numbers.stream().reduce(0, Integer::sum));
        System.out.println(numbers.stream().reduce(0, Integer::max));
        System.out.println(numbers.stream().map(integer -> 1).reduce(Integer::sum));
        System.out.println(numbers.stream().count());
        System.out.println(numbers.stream().mapToInt(Integer::intValue).sum());
        System.out.println(numbers.stream().mapToInt(Integer::intValue).max());
        System.out.println(numbers.stream().collect(Collectors.counting()));
        System.out.println(numbers.stream().collect(Collectors.maxBy(Comparator.comparing(Integer::intValue))));
        System.out.println(numbers.stream().collect(Collectors.summingInt(Integer::intValue)));
        System.out.println(numbers.stream().collect(Collectors.averagingInt(Integer::intValue)));
        System.out.println(numbers.stream().collect(Collectors.summarizingInt(Integer::intValue)));
    }

    @Test
    public void test9() {
        List<Transaction> transactions = transactionList();
        // (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        // (2) 交易员都在哪些不同的城市工作过？
        // (3) 查找所有来自于剑桥的交易员，并按姓名排序。
        // (4) 返回所有交易员的姓名字符串，按字母顺序排序。
        // (5) 有没有交易员是在米兰工作的？
        // (6) 打印生活在剑桥的交易员的所有交易额。
        // (7) 所有交易中，最高的交易额是多少？
        // (8) 找到交易额最小的交易。
        System.out.println(transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue)).collect(toList()));

        System.out.println(transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct().collect(toList()));

        System.out.println(transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(transaction -> transaction.getTrader().getName()).distinct()
                .sorted(String::compareTo).collect(toList()));

        System.out.println(transactions.stream().map(transaction -> transaction.getTrader().getName().split(""))
                .flatMap(Arrays::stream).distinct().sorted(String::compareTo).collect(Collectors.joining()));

        System.out.println(transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan")));

        System.out.println(transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(transaction -> transaction.getValue())
                .reduce(0, (t1, t2) -> t1 + t2));

        System.out.println(transactions.stream().map(transaction -> transaction.getValue()).reduce(Integer::max).get());
        System.out.println(transactions.stream().map(transaction -> transaction.getValue()).max(Integer::compareTo).get());

        System.out.println(transactions.stream().min(Comparator.comparing(Transaction::getValue)).get());
    }

    @Test
    public void test10() {
        List<Transaction> transactions = transactionList();
        int totalCalories = transactions.stream().collect(reducing(0, Transaction::getValue, Integer::sum));
        System.out.println(totalCalories);

        System.out.println(transactions.stream().collect(groupingBy(Transaction::getCurrency)));

        System.out.println(transactions.stream().collect(Collectors.counting()));
        System.out.println(transactions.stream().count());

        System.out.println(transactions.stream().collect(maxBy((a, b) -> Integer.compare(a.getValue(), b.getValue()))));

        System.out.println(transactions.stream().collect(summarizingInt(Transaction::getValue)));
    }

    @Test
    public void test11() {
        List<Transaction> transactions = transactionList();

        Map<PriceLevel, List<Transaction>> byPriceLevel = transactions.stream().collect(
                groupingBy(Transaction::priceLevel));
        System.out.println(byPriceLevel);

        Map<PriceLevel, Map<String, List<Transaction>>> byTwoPriceLevel = transactions.stream().collect(
                groupingBy(Transaction::priceLevel, groupingBy(Transaction::getCurrency)));
        System.out.println(byTwoPriceLevel);

        Map<PriceLevel, Long> byPriceLevel1 = transactions.stream().collect(
                groupingBy(Transaction::priceLevel, counting()));
        System.out.println(byPriceLevel1);

        Map<PriceLevel, Transaction> byPriceLevel2 = transactions.stream().collect(
                groupingBy(Transaction::priceLevel
                        , collectingAndThen(
                                maxBy(Comparator.comparingInt(Transaction::getValue))
                                , Optional::get)));
        System.out.println(byPriceLevel2);
    }

    @Test
    public void test13() {
        List<Transaction> transactions = transactionList();

        Map<PriceLevel, Integer> byPriceLevel3 = transactions.stream().collect(
                groupingBy(Transaction::priceLevel, summingInt(Transaction::getValue)));
        System.out.println(byPriceLevel3);

        Map<PriceLevel, Set<String>> byPriceLevel4 = transactions.stream().collect(
                groupingBy(Transaction::priceLevel, mapping(Transaction::getCurrency, toCollection(HashSet::new))));
        System.out.println(byPriceLevel4);

        Map<Boolean, List<Transaction>> partitionedTran =
                transactions.stream().collect(partitioningBy(transaction ->
                        transaction.getValue() > 500
                ));
        System.out.println(partitionedTran);

        Map<Boolean, Map<String, List<Transaction>>> partitionedTranMap =
                transactions.stream().collect(partitioningBy(transaction ->
                                transaction.getValue() > 500
                        , groupingBy(transaction -> transaction.getTrader().getCity())));
        System.out.println(partitionedTranMap);

        List<Transaction> list = transactions.stream().collect(new ToListCollector<>());
        System.out.println(list);
    }

    public static List<Transaction> transactionList() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300, "$"),
                new Transaction(raoul, 2012, 1000, "$"),
                new Transaction(raoul, 2011, 400, "$"),
                new Transaction(raoul, 2011, 800, "$"),
                new Transaction(mario, 2012, 710, "#"),
                new Transaction(mario, 2012, 700, "#"),
                new Transaction(alan, 2012, 950, "#")
        );
        return transactions;
    }

    public static List<Transaction> filter(List<Transaction> list, Predicate<Transaction> predicate) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction e : list) {
            if (predicate.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

}

