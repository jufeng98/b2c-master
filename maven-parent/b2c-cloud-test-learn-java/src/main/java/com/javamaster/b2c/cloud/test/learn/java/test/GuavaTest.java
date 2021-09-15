package com.javamaster.b2c.cloud.test.learn.java.test;

import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author yudong
 * @date 2019/5/9
 */
@Slf4j
public class GuavaTest {

    @Test
    @SneakyThrows
    public void test() {
        String join = Joiner.on(",").skipNulls().join(Lists.newArrayList("hello", null, "world"));
        System.out.println(join);

        Iterable<String> split = Splitter.on(",").trimResults().split("a, b ,c  ");
        System.out.println(split);

        System.out.println(CharMatcher.DIGIT.retainFrom("hello12world"));

        System.out.println(CharMatcher.DIGIT.removeFrom("hello12world"));
    }

    @Test
    @SneakyThrows
    public void test1() {
        List<Integer> integers = Ints.asList(1, 5, 67, 33, 453);
        System.out.println(integers);
        System.out.println(Ints.max(12, 45, 742));
        System.out.println(Ints.tryParse("23"));
        System.out.println(Arrays.toString(Ints.toArray(Lists.newArrayList(12, 34, 56))));

        System.out.println(Doubles.stringConverter().convert("34.54"));
    }

    @Test
    @SneakyThrows
    public void test2() {
        HashMultiset<String> hashMultiset = HashMultiset.create();
        hashMultiset.add("hello");
        hashMultiset.add("hello");
        hashMultiset.add("world");
        System.out.println(hashMultiset.count("hello"));

        LinkedHashMultimap<String, Object> multimap = LinkedHashMultimap.create();
        multimap.put("Accept", "text/html");
        multimap.put("Accept", "application/xml");
        System.out.println(multimap.get("Accept"));

        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("hello", "world");
        System.out.println(biMap.get("hello"));
        System.out.println(biMap.inverse().get("world"));

        HashBasedTable<Double, Double, String> hashBasedTable = HashBasedTable.create();
        hashBasedTable.put(112.342342, 23.432343, "神秘点");
        System.out.println(hashBasedTable.get(112.342342, 23.432343));
        System.out.println(hashBasedTable.rowKeySet());
    }

    @Test
    @SneakyThrows
    public void test3() {
        ImmutableList<String> im = ImmutableList.of("hello", "world");
        System.out.println(im);
    }

    @Test
    @SneakyThrows
    public void test4() {
        try {
            Integer.parseInt("a12");
        } catch (Exception e) {
            String s = Throwables.getStackTraceAsString(e);
            System.out.println(s);
        }
    }
}
