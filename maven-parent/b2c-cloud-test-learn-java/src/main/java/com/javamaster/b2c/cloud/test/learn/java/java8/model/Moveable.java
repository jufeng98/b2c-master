package com.javamaster.b2c.cloud.test.learn.java.java8.model;

/**
 * Created on 2019/1/8.<br/>
 *
 * @author yudong
 */
public interface Moveable {
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);
    default void moveHorizontally(int distance){
        setX(getX() + distance);
    }
    default void moveVertically(int distance){
        setY(getY() + distance);
    }
}
