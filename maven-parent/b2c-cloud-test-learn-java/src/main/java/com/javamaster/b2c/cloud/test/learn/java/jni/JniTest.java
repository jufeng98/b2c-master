package com.javamaster.b2c.cloud.test.learn.java.jni;

import org.junit.Test;

/**
 * @author yudong
 * @date 2021/8/13
 */
public class JniTest {
    static {
        String property = System.getProperty("java.library.path");
        System.out.println(property);

        System.loadLibrary("HelloNative");
    }

    @Test
    public void test() {
        System.out.println("start");
        HelloNative.greeting();
    }

}
