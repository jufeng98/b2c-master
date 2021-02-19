package com.javamaster.b2c.cloud.test.learn.java.java8.observe;

/**
 * Created on 2019/1/7.<br/>
 *
 * @author yudong
 */
interface Subject {
    void registerObserver(Observer o);

    void notifyObservers(String tweet);
}
