package com.javamaster.b2c.cloud.test.learn.java.lombok;

import lombok.Value;

import java.util.Date;

/**
 * <p>生成不可变的对象</p>
 * Created on 2019/2/2.<br/>
 *
 * @author yudong
 */
@Value
public class Student1 {
    private Integer studId;
    private String name;
    private String email;
    private Date dob;
}
