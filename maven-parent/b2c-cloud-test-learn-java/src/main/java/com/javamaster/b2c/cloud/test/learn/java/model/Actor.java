package com.javamaster.b2c.cloud.test.learn.java.model;

import java.sql.Timestamp;

/**
 * @author yu
 * @date 2018/4/28
 */
public class Actor {

    private Integer actorId;
    private String firstName;
    private String lastName;
    private Timestamp lastUpdate;

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
