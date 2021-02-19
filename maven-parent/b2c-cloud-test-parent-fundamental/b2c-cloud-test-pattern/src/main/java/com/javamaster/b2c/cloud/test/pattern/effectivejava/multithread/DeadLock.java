package com.javamaster.b2c.cloud.test.pattern.effectivejava.multithread;

import java.util.Observable;

/**
 * jconsole 添加这些vm参数
 * -Dcom.sun.management.jmxremote
 * -Dcom.sun.management.jmxremote.port=8011
 * -Dcom.sun.management.jmxremote.ssl=false
 * -Dcom.sun.management.jmxremote.authenticate=false
 * 才不会连接失败
 * Created on 2018/8/10.<br/>
 *
 * @author yudong
 */
public class DeadLock {


    public static void main(String[] args) {
        ProductList productList = new ProductList();
        Product1List product1List = new Product1List();

        productList.setProduct1List(product1List);
        product1List.setProductList(productList);

        Thread thread1 = new Thread(() -> {
            productList.add(new Observable());
        });

        Thread thread2 = new Thread(() -> {
            product1List.add(new Observable());
        });

        thread1.start();
        thread2.start();

    }
}
