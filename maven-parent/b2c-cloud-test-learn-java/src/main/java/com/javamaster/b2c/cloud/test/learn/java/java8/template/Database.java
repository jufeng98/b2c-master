package com.javamaster.b2c.cloud.test.learn.java.java8.template;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Created on 2019/1/7.<br/>
 *
 * @author yudong
 */
public class Database {
    public static Customer getCustomerWithId(int id) {
        return new Customer(RandomStringUtils.randomAlphabetic(6) + id);
    }
}
