package com.javamaster.b2c.cloud.test.learn.java.highquality;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

/**
 * @author yudong
 * @date 2018/4/25
 */
public class ChineseSortTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        Collections.sort(list);
        System.out.println(list);
        Collections.sort(list, Collator.getInstance(Locale.CHINESE));
        System.out.println(list);

        Integer[] scores = new Integer[]{99, 99, 97, 96, 86, 78, 67};
        List<Integer> list1 = Arrays.asList(scores);
        TreeSet<Integer> treeSet = new TreeSet<>(list1);
        System.out.println(treeSet.last());
        System.out.println(treeSet.lower(treeSet.last()));
        System.out.println(treeSet.lower(treeSet.lower(treeSet.last())));
        System.out.println(treeSet);
        System.out.println(treeSet.subSet(treeSet.size() - 3, treeSet.size()));
    }


}
