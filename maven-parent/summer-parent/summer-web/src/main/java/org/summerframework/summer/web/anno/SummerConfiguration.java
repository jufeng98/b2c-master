package org.summerframework.summer.web.anno;

import org.summerframework.summer.core.anno.SummerComponent;

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
public @interface SummerConfiguration {
}
