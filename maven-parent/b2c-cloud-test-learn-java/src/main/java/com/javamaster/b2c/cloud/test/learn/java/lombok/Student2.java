package com.javamaster.b2c.cloud.test.learn.java.lombok;

import lombok.Data;

import java.util.Date;

/**
 * Created on 2019/2/2.<br/>
 *
 * @author yudong
 */
@Data
public class Student2 {
    private Integer studId;
    private String name;
    private String email;
    private Date dob;
}
