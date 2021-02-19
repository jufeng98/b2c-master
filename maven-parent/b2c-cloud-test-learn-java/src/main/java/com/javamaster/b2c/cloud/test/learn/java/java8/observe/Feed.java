package com.javamaster.b2c.cloud.test.learn.java.java8.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/1/7.<br/>
 *
 * @author yudong
 */
class Feed implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers(String tweet) {
        observers.forEach(o -> o.notify(tweet));
    }
}
