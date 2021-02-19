package com.javamaster.b2c.cloud.test.learn.java.java8.model;

/**
 * Created on 2019/1/7.<br/>
 *
 * @author yudong
 */
public class Accumulator {
    public long total = 0;

    public void add(long value) {
        total += value;
    }
}
