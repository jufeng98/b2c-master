package org.javamaster.spring.test.annos;

import java.lang.annotation.*;

/**
 * @author yudong
 * @date 2021/5/15
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ScanTestedDependencies {
    Class<?> value();
}
