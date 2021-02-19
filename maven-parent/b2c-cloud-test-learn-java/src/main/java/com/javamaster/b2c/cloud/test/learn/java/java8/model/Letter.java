package com.javamaster.b2c.cloud.test.learn.java.java8.model;

/**
 * Created on 2018/12/17.<br/>
 *
 * @author yudong
 */
public class Letter {
    public static String addHeader(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }

    public static String addFooter(String text) {
        return text + " Kind regards";
    }

    public static String checkSpelling(String text) {
        return text.replaceAll("labda", "lambda");
    }
}
