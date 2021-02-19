package com.javamaster.b2c.jsp.entity;

import lombok.Data;

/**
 * @author yudong
 * @date 2020/3/18
 */
@Data
public class SysDict {
    private String id;
    private String value;
    private String label;
    private String type;
    private String description;
    private Integer sort;
    private String parentId;
}