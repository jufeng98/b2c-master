package org.javamaster.b2c.test.learn.java;

public interface Card {

    /**
     * 接口私有方法,Java9新增特性
     */
    private String createCardId() {
        return "hello world";
    }

    /**
     * 默认方法,Java8新增特性
     */
    default void displayCardDetails() {
        System.out.println(createCardId());
    }

}
