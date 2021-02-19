package com.javamaster.b2c.cloud.test.learn.java.highquality;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yudong
 * @date 2018/4/25
 */
public class BoxingTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(null);
        System.out.println(sum(list));
    }

    public static int sum(List<Integer> list) {
        int count = 0;
        for (Integer i : list) {
            count += i;
        }
        return count;
    }

}
