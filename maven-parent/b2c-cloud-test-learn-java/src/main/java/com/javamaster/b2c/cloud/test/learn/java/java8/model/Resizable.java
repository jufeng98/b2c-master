package com.javamaster.b2c.cloud.test.learn.java.java8.model;

/**
 * Created on 2019/1/8.<br/>
 *
 * @author yudong
 */
public interface Resizable {
    int getWidth();

    int getHeight();

    void setWidth(int width);

    void setHeight(int height);

    void setAbsoluteSize(int width, int height);

    default void setRelativeSize(int wFactor, int hFactor) {
        setAbsoluteSize(getWidth() / wFactor, getHeight() / hFactor);
    }
}
