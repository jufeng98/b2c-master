package com.javamaster.b2c.cloud.test.learn.java.model;

import com.javamaster.b2c.cloud.test.learn.java.annotation.CheckCase;
import com.javamaster.b2c.cloud.test.learn.java.enums.CaseMode;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu
 * @date 2018/3/22
 */
public class Person {


    private long personId = 0;
    @NotBlank
    @CheckCase(value = CaseMode.UPPER, message = "名字必须为大写")
    private String name;
    public Address address;
    public Date birthday;

    private Map<String, Object> unreconized = new HashMap<>();

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @JsonAnySetter
    public void setUnreconized(String key, Object value) {
        unreconized.put(key, value);
    }

    public long getPersonId() {
        return this.personId;
    }

    @JsonSetter("id")
    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Map<String, Object> getUnreconized() {
        return unreconized;
    }

    public void setUnreconized(Map<String, Object> unreconized) {
        this.unreconized = unreconized;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}