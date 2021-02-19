package com.javamaster.b2c.cloud.test.security.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by yu on 2018/3/26.
 */
public class Authority implements GrantedAuthority {
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}

