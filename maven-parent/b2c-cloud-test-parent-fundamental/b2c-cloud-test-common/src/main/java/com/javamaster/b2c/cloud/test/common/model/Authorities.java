package com.javamaster.b2c.cloud.test.common.model;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public class Authorities implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 5528719873999390749L;
    private String username;

    private String authority;

    @Override
    public String toString() {
        return "Authorities{" +
                "username='" + username + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }

    public static Authorities getInstance() {
        return new Authorities();
    }

    public Authorities username(String username) {
        this.username = username;
        return this;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return authority
     */
    @Override
    public String getAuthority() {
        return authority;
    }

    /**
     * @param authority
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }
}