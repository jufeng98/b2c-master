package com.javamaster.b2c.cloud.test.learn.java.java8.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created on 2019/1/7.<br/>
 *
 * @author yudong
 */
public class FactoryTest {
    final static Map<String, Supplier<Product>> map = new HashMap<>();
    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }
    public static void main(String[] args) {

        Product p = ProductFactory.createProduct("loan");
        System.out.println(p);

        Supplier<Product> p1 = map.get("loan");
        System.out.println(p1.get());

    }
}
