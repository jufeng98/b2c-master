package com.javamaster.b2c.cloud.test.learn.java.highquality;

/**
 * @author yudong
 * @date 2018/4/26
 */
public class StackInfoTest {
    public static void main(String[] args) {
        say();
    }

    static void say() {
        System.out.println("hello world");
        Throwable throwable = new Throwable();
        for (StackTraceElement traceElement : throwable.getStackTrace()) {
            System.out.println(traceElement);
        }
    }
}
