package com.javamaster.b2c.cloud.test.learn.java.java8.strategy;

/**
 * Created on 2019/1/7.<br/>
 *
 * @author yudong
 */
public class Validator {
    private final ValidationStrategy strategy;

    public Validator(ValidationStrategy v) {
        this.strategy = v;
    }

    public boolean validate(String s) {
        return strategy.execute(s);
    }
}
