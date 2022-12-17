package com.javamaster.b2c.cloud.test.learn.java.model;

import com.javamaster.b2c.cloud.test.learn.java.json.OptimizedBooleanDeserializer;
import com.javamaster.b2c.cloud.test.learn.java.json.OptimizedBooleanSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by yu on 2018/3/22.
 */
public class PersonDeserialize {

    private long id = 0;
    private String name = null;
    private int age;

    @JsonSerialize(using = OptimizedBooleanSerializer.class)
    @JsonDeserialize(using = OptimizedBooleanDeserializer.class)
    private boolean enabled = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
