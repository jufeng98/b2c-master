package com.javamaster.b2c.cloud.test.learn.java.json;

import com.javamaster.b2c.cloud.test.learn.java.model.Car1;
import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

/**
 * @author yudong
 * @date 2020/9/21
 */
public class CarCreator implements InstanceCreator<Car1> {
    @Override
    public Car1 createInstance(Type type) {
        Car1 car = new Car1();
        car.setBrand("Toyota");
        return car;
    }
}
