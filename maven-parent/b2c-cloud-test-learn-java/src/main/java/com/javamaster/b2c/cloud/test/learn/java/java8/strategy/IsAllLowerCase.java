package com.javamaster.b2c.cloud.test.learn.java.java8.strategy;

/**
 * Created on 2019/1/7.<br/>
 *
 * @author yudong
 */
public class IsAllLowerCase implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("[a-z]+");
    }
}
