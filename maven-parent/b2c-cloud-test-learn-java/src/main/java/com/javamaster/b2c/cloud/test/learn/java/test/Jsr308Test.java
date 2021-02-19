package com.javamaster.b2c.cloud.test.learn.java.test;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

/**
 * @author yudong
 * @date 2019/5/10
 */
public class Jsr308Test {
    @Test
    public void test() {
        String str = "hello";
        System.out.println(length(str));
    }

    @Contract
    private int length(@NotNull String string) {
        @NotNull String s1 = null;
        return string.length() ;
    }
}
