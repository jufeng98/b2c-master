package org.javamaster.annotation.processor.annotation;

import java.lang.annotation.*;

/**
 * Created on 2019/1/12.<br/>
 *
 * @author yudong
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface GenerateGetMethod {
}
