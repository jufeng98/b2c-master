package com.javamaster.b2c.cloud.test.learn.java.java8.ppt;

import com.alibaba.fastjson.JSONObject;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created on 2018/12/19.<br/>
 *
 * @author yudong
 */
public class StreamTest {

    @Test
    public void test() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(null, 2018, 5, 1200, "AR"),
                new Transaction(null, 2018, 8, 1800, "AR"),
                new Transaction(null, 2019, 5, 2900, "AR"),
                new Transaction(null, 2018, 7, 1800, "AR"),
                new Transaction(null, 2018, 7, 8300, "CH"),
                new Transaction(null, 2019, 7, 8900, "CH"),
                new Transaction(null, 2019, 7, 1000, "CH")
        );
        Supplier<Stream<TransactionVo>> supplier = () -> transactions.stream()
                .filter(transaction -> transaction.getValue() > 1500)
                .sorted(Comparator.comparing(Transaction::getYear).reversed().thenComparing(Transaction::getMonth))
                .map(transaction -> {
                    TransactionVo transactionVo = new TransactionVo();
                    BeanUtils.copyProperties(transaction, transactionVo);
                    return transactionVo;
                });

        Map<String, List<TransactionVo>> map = supplier.get()
                .collect(groupingBy(transactionVo -> transactionVo.getCurrency()));
        System.out.println(JSONObject.toJSONString(map, true));
        System.out.println("---");

        Map<String, Map<Integer, List<TransactionVo>>> map1 = supplier.get()
                .collect(groupingBy(TransactionVo::getCurrency, groupingBy(TransactionVo::getYear)));
        System.out.println(JSONObject.toJSONString(map1, true));

        Map<String, Long> map2 = supplier.get()
                .collect(groupingBy(TransactionVo::getCurrency, counting()));
        System.out.println(JSONObject.toJSONString(map2, true));
        System.out.println("---");

        Map<String, TransactionVo> map3 = supplier.get()
                .collect(
                        groupingBy(TransactionVo::getCurrency,
                                collectingAndThen(
                                        maxBy(Comparator.comparingInt(TransactionVo::getValue)), Optional::get
                                )
                        )
                );
        System.out.println(JSONObject.toJSONString(map3, true));
        System.out.println("---");

        Map<String, Integer> map4 = supplier.get()
                .collect(
                        groupingBy(TransactionVo::getCurrency, summingInt(TransactionVo::getValue))
                );
        System.out.println(JSONObject.toJSONString(map4, true));
        System.out.println("---");

        Map<Boolean, List<TransactionVo>> map5 = supplier.get()
                .collect(
                        partitioningBy(transactionVo -> transactionVo.getValue() >= 5000)
                );
        System.out.println(JSONObject.toJSONString(map5, true));
        System.out.println("---");
    }

    @Test
    public void test1() {
        List<String> list = Arrays.asList("hello", "world");
        System.out.println(list.stream().map(s -> s.split("")).distinct().collect(Collectors.toList()));

        String string = StringUtils.join(list);
        System.out.println(Stream.of(string).map(s -> s.split("")).distinct().collect(toList()));

        System.out.println(list.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(toList()));
        System.out.println(list.stream().map(s -> s.split("")).flatMap(Arrays::stream).collect(toSet()));
        System.out.println(list.stream().map(s -> s.split("")).flatMap(Arrays::stream).collect(toCollection(() -> new HashSet<>())));
        System.out.println(list.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.joining("|")));
    }
}
