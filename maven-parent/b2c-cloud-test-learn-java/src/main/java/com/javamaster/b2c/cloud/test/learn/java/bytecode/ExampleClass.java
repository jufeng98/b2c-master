package com.javamaster.b2c.cloud.test.learn.java.bytecode;

import com.javamaster.b2c.cloud.test.learn.java.annotation.TestInvisible;
import com.javamaster.b2c.cloud.test.learn.java.annotation.TestVisible;
import com.javamaster.b2c.cloud.test.learn.java.model.Car;
import com.javamaster.b2c.cloud.test.learn.java.validation.CarChecks;
import com.javamaster.b2c.cloud.test.learn.java.validation.DriverChecks;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Created on 2019/1/11.<br/>
 *
 * @author yudong
 */
@Component
@Deprecated
public class ExampleClass<K, U extends Number> extends Car implements CarChecks, DriverChecks {

    @TestVisible(value = "hello")
    @TestInvisible(value = "world")
    private int age;

    @NotNull(message = "data不能为null")
    public Map<K, U> data;

    public static final String KEY = "key";

    public static final Integer VALUE = 88;

    @Transactional(rollbackFor = FileNotFoundException.class)
    public int increaseAge(Integer theMaxValues) throws FileNotFoundException {
        try {

            new InnerClass() {
            };

            return age + 1;
        } catch (Exception e) {
            return -1;
        }
    }

    public U get() {
        U t = null;
        return t;
    }

    private class InnerClass {

    }
}
