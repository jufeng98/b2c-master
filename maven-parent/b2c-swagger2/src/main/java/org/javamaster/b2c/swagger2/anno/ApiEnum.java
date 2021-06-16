package org.javamaster.b2c.swagger2.anno;

import org.javamaster.b2c.swagger2.enums.EnumBase;

import java.lang.annotation.*;

/**
 * @author yudong
 * @date 2021/6/16
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiEnum {
    Class<? extends Enum<? extends EnumBase>> value();
}
