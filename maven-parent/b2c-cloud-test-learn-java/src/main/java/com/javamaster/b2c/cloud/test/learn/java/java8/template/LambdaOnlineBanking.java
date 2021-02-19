package com.javamaster.b2c.cloud.test.learn.java.java8.template;

import java.util.function.Consumer;

/**
 * Created on 2019/1/7.<br/>
 *
 * @author yudong
 */
public class LambdaOnlineBanking {
    public void processCustomer(int id, Consumer<Customer> consumer) {
        Customer c = Database.getCustomerWithId(id);
        consumer.accept(c);
    }
}
