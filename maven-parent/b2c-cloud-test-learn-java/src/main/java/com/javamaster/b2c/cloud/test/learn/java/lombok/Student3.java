package com.javamaster.b2c.cloud.test.learn.java.lombok;

import lombok.Builder;
import lombok.ToString;

import java.util.Date;

/**
 * Created on 2019/2/2.<br/>
 *
 * @author yudong
 */
@Builder
@ToString
public class Student3 {
    private Integer studId;
    private String name;
    private String email;
    private Date dob;
}
