package com.javamaster.b2c.cloud.test.learn.java.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by yu on 2018/3/22.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonInclude {
    private Long personId = 0L;
    private String name;
    private Integer age;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
