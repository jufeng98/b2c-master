package com.javamaster.b2c.cloud.test.user.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2019/1/13.<br/>
 *
 * @author yudong
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Transaction {
    /**
     * 指定发生何种异常回滚事务
     */
    Class<? extends Exception> rollbackFor() default Exception.class;
}
