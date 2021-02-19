package com.javamaster.b2c.cloud.test.learn.java.java8.optional;

import java.util.Optional;

/**
 * Created on 2019/1/8.<br/>
 *
 * @author yudong
 */
public class OptionalUtils {

    public static <T> Optional<T> getValue(T value) {
        return Optional.ofNullable(value);
    }

    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
