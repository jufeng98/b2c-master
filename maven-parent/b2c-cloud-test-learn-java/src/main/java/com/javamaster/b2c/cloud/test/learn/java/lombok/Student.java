package com.javamaster.b2c.cloud.test.learn.java.lombok;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created on 2019/2/2.<br/>
 *
 * @author yudong
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Student {
    private Integer studId;
    private String name;
    private String email;
    private Date dob;
}
