package com.javamaster.b2c.cloud.test.user.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by yu on 2018/4/28.
 */
public class Actor implements Serializable {

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
