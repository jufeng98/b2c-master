package com.javamaster.b2c.cloud.test.learn.java.java8.template;

/**
 * Created on 2019/1/7.<br/>
 *
 * @author yudong
 */
public abstract class OnlineBanking {
    public void processCustomer(int id) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }

    protected abstract void makeCustomerHappy(Customer c);
}
