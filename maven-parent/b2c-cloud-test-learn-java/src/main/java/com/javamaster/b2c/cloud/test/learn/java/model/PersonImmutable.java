package com.javamaster.b2c.cloud.test.learn.java.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yu on 2018/3/22.
 */
public class PersonImmutable {

    private long id;
    private String name;

    @JsonCreator
    public PersonImmutable(
            @JsonProperty("id") long id,
            @JsonProperty("name") String name) {

        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}

