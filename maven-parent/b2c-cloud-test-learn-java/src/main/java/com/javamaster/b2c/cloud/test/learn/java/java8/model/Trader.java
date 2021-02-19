package com.javamaster.b2c.cloud.test.learn.java.java8.model;

/**
 * Created on 2018/12/19.<br/>
 *
 * @author yudong
 */
public class Trader {
    private String name;
    private String city;

    public Trader() {
    }

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
