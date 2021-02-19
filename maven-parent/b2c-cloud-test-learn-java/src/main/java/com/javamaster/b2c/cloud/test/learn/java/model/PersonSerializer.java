package com.javamaster.b2c.cloud.test.learn.java.model;

import com.javamaster.b2c.cloud.test.learn.java.jackson.OptimizedBooleanSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by yu on 2018/3/22.
 */
public class PersonSerializer {

    public long personId = 0;
    public String name = "John";
    @JsonSerialize(using = OptimizedBooleanSerializer.class)
    public boolean enabled = true;

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
