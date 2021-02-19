package com.javamaster.b2c.cloud.test.learn.java.model;

import com.fasterxml.jackson.annotation.JsonRawValue;

/**
 * Created by yu on 2018/3/22.
 */
public class PersonRawValue {
    public long personId = 0;
    @JsonRawValue
    public String address = "{ \"street\" : \"Wall Street\", \"no\":1}";
}
