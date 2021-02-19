package com.javamaster.b2c.cloud.test.learn.java.jdbc.model;

import lombok.Data;

import java.util.List;

/**
 * @author yudong
 * @date 2019/7/9
 */
@Data
public class Database {
    /**
     * 已知的用户
     */
    private String userName;
    /**
     * 系统函数的逗号分隔列表
     */
    private String systemFunctions;
    /**
     * 时间和日期函数的逗号分隔列表
     */
    private String timeDateFunctions;
    /**
     * 字符串函数的逗号分隔列表
     */
    private String stringFunctions;
    /**
     * 供应商用于 'schema' 的首选术语
     */
    private String schemaTerm;
    private String url;
    private boolean readOnly;
    /**
     * 产品名称
     */
    private String databaseProductName;
    /**
     * 版本
     */
    private String databaseProductVersion;
    private String driverName;
    private String driverVersion;
    /**
     * 使用的表类型
     */
    private List<String> tableTypes;
    private boolean supportsGetGeneratedKeys;
    private boolean supportsGroupBy;
    private boolean supportsOuterJoins;
    private int maxStatements;
}
