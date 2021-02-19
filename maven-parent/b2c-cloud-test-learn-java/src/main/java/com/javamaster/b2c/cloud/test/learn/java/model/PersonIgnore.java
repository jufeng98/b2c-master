package com.javamaster.b2c.cloud.test.learn.java.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by yu on 2018/3/22.
 */
public class PersonIgnore {

    @JsonIgnore
    public long personId = 0;

    public String name = null;
}