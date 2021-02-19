package com.javamaster.b2c.cloud.test.learn.java.model;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MallAdviserCustomerInfo {
    private Long id;
    private String name;
    private Long mobile;
    private String wxNo;
    private String province;
    private Integer provinceCode;
    private String city;
    private Integer cityCode;
    private String county;
    private Integer countyCode;
    private String address;
    private String source;
    private Boolean autonym;
    private String userId;
    private String userName;
    private String angelCode;
    private Date orderTime;
    private String labelType;
    private Integer binging;
    private Date registerTime;
    private Date bingingTime;
    private Date loginTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MallAdviserCustomerInfo info = (MallAdviserCustomerInfo) o;
        return Objects.equal(mobile, info.mobile) &&
                Objects.equal(userId, info.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mobile, userId);
    }
}