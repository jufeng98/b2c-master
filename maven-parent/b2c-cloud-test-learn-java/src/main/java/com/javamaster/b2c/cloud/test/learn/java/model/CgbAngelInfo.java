package com.javamaster.b2c.cloud.test.learn.java.model;

import lombok.Data;

import java.util.Date;

/**
 * @author yudong
 * @date 2020/3/18
 */
@Data
public class CgbAngelInfo {
    private Long id;
    private String reqNo;
    private String approveName;
    private String approveBy;
    private String userid;
    private Integer status;
    private Long angelId;
    private String angelName;
    private Integer grouperIdentity;
    private String angelNo;
    private Long phone;
    private String groupName;
    private Integer provinceCode;
    private String provinceName;
    private Integer cityCode;
    private String cityName;
    private Integer countyCode;
    private String countyName;
    private String community;
    private String receiveAddrName;
    private String receiveAddr;
    private String receiveDay;
    private Date receiveStartTime;
    private Date receiveEndTime;
    private Integer wechatGroupNum;
    private Integer wechatPersonNum;
    private Date submitTime;
    private Date approveTime;
    private String longitude;
    private String latitude;
    private Integer valid;
}