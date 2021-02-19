package com.javamaster.b2c.cloud.test.pattern.effectivejava.multithread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

/**
 * Created on 2018/8/10.<br/>
 *
 * @author yudong
 */
public class ObserveableList implements Iterable<Observable> {

    private List<Observable> observables = new ArrayList<>();

    public static void main(String[] args) {

        final ObserveableList observeableList = new ObserveableList();

        observeableList.add(new Observable());
        observeableList.add(new Observable());
        observeableList.add(new Observable());

        Thread iteratorThread = new Thread(() -> {
            for (Observable observable : observeableList) {
                System.out.println(observable);
            }
        });

        Thread removeThread = new Thread(() -> {
            observeableList.remove(1);
        });

        iteratorThread.start();
        removeThread.start();
    }

    public void add(Observable observable) {
        synchronized (this) {
            observables.add(observable);
        }
    }

    public void remove(int i) {
        synchronized (this) {
            observables.remove(i);
        }
    }

    public Iterator<Observable> iterator() {
        return observables.iterator();
    }
}
