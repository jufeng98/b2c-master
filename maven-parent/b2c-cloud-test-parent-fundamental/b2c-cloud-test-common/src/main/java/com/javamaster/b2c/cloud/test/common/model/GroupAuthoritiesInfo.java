package com.javamaster.b2c.cloud.test.common.model;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created on 2018/12/9.<br/>
 *
 * @author yudong
 */
public class GroupAuthoritiesInfo implements Serializable {
    private static final long serialVersionUID = -6460230584288762513L;
    private BigInteger id;
    private String groupName;
    private String authority;

    @Override
    public String toString() {
        return "GroupAuthoritiesInfo{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
