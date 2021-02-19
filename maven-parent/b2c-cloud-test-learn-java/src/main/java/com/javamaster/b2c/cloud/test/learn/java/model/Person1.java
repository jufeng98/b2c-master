package com.javamaster.b2c.cloud.test.learn.java.model;

import com.google.gson.annotations.Since;
import lombok.Data;

/**
 * @author yudong
 * @date 2020/9/21
 */
@Data
public class Person1 {
    @Since(1.0)
    public String firstName = null;

    @Since(1.0)
    public String lastName = null;

    @Since(2.0)
    public String middleName = null;

    @Since(3.0)
    public String email = null;
}
