package com.javamaster.b2c.cloud.test.learn.java.java8.template;

/**
 * Created on 2019/1/7.<br/>
 *
 * @author yudong
 */
public class TemplateTest {
    public static void main(String[] args) {
        OnlineBanking banking = new GuangzhouBranchBanking();
        banking.processCustomer(23);

        LambdaOnlineBanking lambdaOnlineBanking = new LambdaOnlineBanking();
        lambdaOnlineBanking.processCustomer(23, customer -> System.out.println("offer tea to " + customer.getName()));
    }
}
