package com.javamaster.b2c.cloud.test.pattern.effectivejava.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created on 2018/8/10.<br/>
 *
 * @author yudong
 */
public class ProductList {

    private Product1List product1List;
    private List<Observable> observables = new ArrayList<>();

    public void add(Observable observable) {
        synchronized (this) {
            observables.add(observable);
            product1List.add(observable);
        }
    }

    public void remove(int i) {
        synchronized (this) {
            observables.remove(i);
        }
    }

    public void setProduct1List(Product1List product1List) {
        this.product1List = product1List;
    }
}
