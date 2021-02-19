package com.javamaster.b2c.cloud.test.learn.java.mybatis.model;

import java.util.Date;


/**
 * token信息表
 *
 * @author yu
 * @email yu@qq.com
 * @date 2019-03-21 08:47:31
 */
public class BmMicrowebsiteAppTokenEntity {
    private static final long serialVersionUID = 1L;

    //主键id
    private Integer id;
    //用户编码
    private Integer userCode;
    //用户名称
    private String userName;
    //登录成功后,服务器端返回的值
    private String token;
    //创建时间
    private Date createTime;
    //最近访问时间
    private Date recentlyTime;
    //是否有效：1-有效，0-无效
    private Integer state;
    //客户端类型
    private String clientType;
    //app类型
    private String appType;
    //唯一机器码
    private String machineId;

    /**
     * 获取：主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：用户编码
     */
    public Integer getUserCode() {
        return userCode;
    }

    /**
     * 设置：用户编码
     */
    public void setUserCode(Integer userCode) {
        this.userCode = userCode;
    }

    /**
     * 获取：用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置：用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取：登录成功后,服务器端返回的值
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置：登录成功后,服务器端返回的值
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：最近访问时间
     */
    public Date getRecentlyTime() {
        return recentlyTime;
    }

    /**
     * 设置：最近访问时间
     */
    public void setRecentlyTime(Date recentlyTime) {
        this.recentlyTime = recentlyTime;
    }

    /**
     * 获取：是否有效：1-有效，0-无效
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置：是否有效：1-有效，0-无效
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取：客户端类型
     */
    public String getClientType() {
        return clientType;
    }

    /**
     * 设置：客户端类型
     */
    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    /**
     * 获取：app类型
     */
    public String getAppType() {
        return appType;
    }

    /**
     * 设置：app类型
     */
    public void setAppType(String appType) {
        this.appType = appType;
    }

    /**
     * 获取：唯一机器码
     */
    public String getMachineId() {
        return machineId;
    }

    /**
     * 设置：唯一机器码
     */
    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }
}
