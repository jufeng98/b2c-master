package com.javamaster.b2c.cloud.test.learn.java.java8.ppt;

/**
 * Created on 2018/12/19.<br/>
 *
 * @author yudong
 */
public class Trader {
    private final String name;
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    @Override
    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}
