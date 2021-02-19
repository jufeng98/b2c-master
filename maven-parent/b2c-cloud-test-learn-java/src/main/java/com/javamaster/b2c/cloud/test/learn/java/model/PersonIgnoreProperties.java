package com.javamaster.b2c.cloud.test.learn.java.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yu on 2018/3/22.
 */

@JsonIgnoreProperties({"firstName", "lastName"})
public class PersonIgnoreProperties {

    public long personId = 0;

    public String firstName = null;
    public String lastName = null;

}
