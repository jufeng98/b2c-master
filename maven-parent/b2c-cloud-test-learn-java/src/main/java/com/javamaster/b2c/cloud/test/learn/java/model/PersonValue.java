package com.javamaster.b2c.cloud.test.learn.java.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by yu on 2018/3/22.
 */
public class PersonValue {
    public long personId = 0;
    public String name = null;

    @JsonValue
    public String toJson() {
        return this.personId + "," + this.name;
    }
}
