package org.javamaster.b2c.mybatis.model;

import lombok.Data;

import java.util.Date;

/**
 * Created on 2019/2/2.<br/>
 *
 * @author yudong
 */
@Data
public class Student {
    private Integer studId;
    private String name;
    private String email;
    private Date dob;
    private PhoneNumber phone;
    private Address address;
    private Gender gender;
    /**
     * 默认情况下， MyBatis将 CLOB 类型的列映射到 java.lang.String 类型上、而把 BLOB 列映射到 byte[] 类型上。
     */
    private byte[] pic;
    private String bio;
}
