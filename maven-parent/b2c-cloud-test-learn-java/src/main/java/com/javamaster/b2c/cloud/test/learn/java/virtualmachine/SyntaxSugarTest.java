package com.javamaster.b2c.cloud.test.learn.java.virtualmachine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/9/10.<br/>
 *
 * @author yudong
 */
public class SyntaxSugarTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hello");
        String s = list.get(0);
        System.out.println(s);

        for (String s1 : list) {
            System.out.println(s1);
        }

        Integer a = 3;
        int b = a;
        System.out.println(b);

        System.out.println(sum(23, 32, 43));

        switch (s) {
            case "hello":
                System.out.println("a");
                break;
            case "world":
                System.out.println("b");
                break;
            default:
                System.out.println("c");
        }

    }

    public static int sum(int... num) {
        int sum = 0;
        for (int i : num) {
            sum += i;
        }
        return sum;
    }
}
