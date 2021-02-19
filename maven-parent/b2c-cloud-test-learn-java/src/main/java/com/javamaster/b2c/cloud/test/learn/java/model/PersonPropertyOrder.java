package com.javamaster.b2c.cloud.test.learn.java.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by yu on 2018/3/22.
 */
@JsonPropertyOrder({"name", "personId"})
public class PersonPropertyOrder {

    public long personId = 0;
    public String name = null;

}
