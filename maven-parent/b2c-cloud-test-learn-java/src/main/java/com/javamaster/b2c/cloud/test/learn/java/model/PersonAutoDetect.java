package com.javamaster.b2c.cloud.test.learn.java.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by yu on 2018/3/22.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PersonAutoDetect {

    private long personId = 123;
    public String name = null;

}
