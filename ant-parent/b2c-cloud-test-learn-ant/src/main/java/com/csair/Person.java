package com.javamaster;

/**
 * Created by yu on 2018/3/22.
 */
public class Person {


    private long personId = 0;
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public long getPersonId() {
        return this.personId;
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
}