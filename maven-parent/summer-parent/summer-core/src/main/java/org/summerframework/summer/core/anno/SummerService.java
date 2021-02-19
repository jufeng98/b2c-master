package org.summerframework.summer.core.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yudong
 * @date 2019/5/13
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SummerComponent
public @interface SummerService {
    String value() default "";
}
