package com.javamaster.b2c.cloud.test.learn.java.java8.factory;

/**
 * Created on 2019/1/7.<br/>
 *
 * @author yudong
 */
public class ProductFactory {
    public static Product createProduct(String name){
        switch(name){
            case "loan": return new Loan();
            case "stock": return new Stock();
            case "bond": return new Bond();
            default: throw new RuntimeException("No such product " + name);
        }
    }
}
