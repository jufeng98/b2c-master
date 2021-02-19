package com.javamaster.b2c.cloud.test.pattern.effectivejava.wrappingtype;

/**
 * Created on 2018/8/10.<br/>
 *
 * @author yudong
 */
public class WrappingType {
    public static void main(String[] args) {

        Integer a = 23;
        Integer b = 23;
        System.out.println(a == b);

        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d);

        Integer e = 128;
        int f = 128;
        System.out.println(e == f);

        Integer g = null;
        int h = 128;
        System.out.println(g == h);

        Long sum = 0L;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println(sum);

    }
}
