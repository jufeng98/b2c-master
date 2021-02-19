package com.javamaster.b2c.cloud.test.learn.java.jdbc.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MallUserAddress {
    private String addressId;
    private String userId;
    private String receiver;
    private String receiverMobile;
    private String receiverPhone;
    private String provinceId;
    private String provinceName;
    private String cityId;
    private String cityName;
    private String countyId;
    private String countyName;
    private String streetId;
    private String streetName;
    private String villageId;
    private String villageName;
    private String address;
    private String postCode;
    private Integer isDefaultInt;
    private Integer status;
    private Date updateTime;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Byte labelType;
    private String labelContent;
    private Byte exceptionFlag;
}