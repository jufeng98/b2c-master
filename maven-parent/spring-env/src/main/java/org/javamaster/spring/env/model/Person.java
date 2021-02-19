package org.javamaster.spring.env.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yudong
 * @date 2020/7/31
 */
@Data
public class Person implements Serializable {

    private static final long serialVersionUID = -3056805657617014112L;
    private String name;
    private Integer age;
}
