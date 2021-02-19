package com.javamaster.b2c.cloud.test.learn.java.model;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

/**
 * Created by yu on 2018/3/22.
 */
public class PersonIgnoreType {

    @JsonIgnoreType
    public static class Address {
        public String streetName = null;
        public String houseNumber = null;
        public String zipCode = null;
        public String city = null;
        public String country = null;
    }

    public long personId = 0;

    public String name = null;

    public Address address = null;
}
