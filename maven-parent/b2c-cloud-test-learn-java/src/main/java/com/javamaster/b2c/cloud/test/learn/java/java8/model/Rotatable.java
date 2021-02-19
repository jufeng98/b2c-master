package com.javamaster.b2c.cloud.test.learn.java.java8.model;

/**
 * Created on 2019/1/8.<br/>
 *
 * @author yudong
 */
public interface Rotatable {
    void setRotationAngle(int angleInDegrees);

    int getRotationAngle();

    default void rotateBy(int angleInDegrees) {
        setRotationAngle((getRotationAngle() + angleInDegrees) % 360);
    }
}
