package com.javamaster.b2c.cloud.test.learn.java.java8.template;

/**
 * Created on 2019/1/7.<br/>
 *
 * @author yudong
 */
public class GuangzhouBranchBanking extends OnlineBanking {
    @Override
    public void makeCustomerHappy(Customer c) {
        System.out.println("offer tea to " + c.getName());
    }
}
