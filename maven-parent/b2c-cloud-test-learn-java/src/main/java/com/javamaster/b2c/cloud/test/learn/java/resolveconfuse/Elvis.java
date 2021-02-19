package com.javamaster.b2c.cloud.test.learn.java.resolveconfuse;

import java.util.Calendar;

/**
 * @author yudong
 * @date 2018/4/19
 */
public class Elvis {
    public static final Elvis INSTANCE = new Elvis();
    private final int beltSize;
    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

    private Elvis() {
        beltSize = CURRENT_YEAR - 1930;
    }

    public int beltSize() {
        return beltSize;
    }

    public static void main(String[] args) {
        System.out.println("Elvis wears a size " + INSTANCE.beltSize() + "belt.");
    }
}