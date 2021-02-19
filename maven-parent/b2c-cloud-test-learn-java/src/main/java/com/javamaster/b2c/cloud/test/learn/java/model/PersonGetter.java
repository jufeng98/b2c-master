package com.javamaster.b2c.cloud.test.learn.java.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by yu on 2018/3/22.
 */
public class PersonGetter {

    private long personId = 0;

    @JsonGetter("id")
    public long getPersonId() {
        return personId;
    }


    @JsonSetter("id")
    public void setPersonId(long personId) {
        this.personId = personId;
    }
}
