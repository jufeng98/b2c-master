package com.javamaster.b2c.cloud.test.learn.java.model;

import com.fasterxml.jackson.annotation.JacksonInject;

/**
 * Created by yu on 2018/3/22.
 */
public class PersonInject {
    public long   id   = 0;
    public String name = null;

    @JacksonInject
    public String source = null;
}
