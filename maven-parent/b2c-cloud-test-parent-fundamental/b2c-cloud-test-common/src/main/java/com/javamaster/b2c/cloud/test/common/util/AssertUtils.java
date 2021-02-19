package com.javamaster.b2c.cloud.test.common.util;

/**
 * Created on 2019/1/12.<br/>
 *
 * @author yudong
 */
public class AssertUtils {

    public static void isNotEmpty(String o, String msg) {
        if (o == null || o.trim().equals("")) {
            throw new IllegalArgumentException(msg);
        }
    }

}
