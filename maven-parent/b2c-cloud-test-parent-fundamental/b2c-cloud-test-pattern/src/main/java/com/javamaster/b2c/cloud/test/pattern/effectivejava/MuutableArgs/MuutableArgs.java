package com.javamaster.b2c.cloud.test.pattern.effectivejava.MuutableArgs;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 2018/8/8.<br/>
 *
 * @author yudong
 */
public class MuutableArgs {
    public static void main(String[] args) {
        System.out.println(sum(12, 2));

        // 错误做法
        int[] digits = {2, 3, 4, 5};
        List list = Arrays.asList(digits);
        System.out.println(list.size());
        for (Object o : list) {
            System.out.println(o.getClass().getName() + " " + o);
        }

        // 正确做法
        Integer[] digitss = {2, 3, 4, 5};
        List lists = Arrays.asList(digitss);
        System.out.println(lists.size());
        for (Object o : lists) {
            System.out.println(o.getClass().getName() + " " + o);
        }
    }

    public static int sum(int... args) {
        int sum = 0;
        if (args == null) {
            return sum;
        }
        for (int arg : args) {
            sum += arg;
        }
        return sum;
    }
}
