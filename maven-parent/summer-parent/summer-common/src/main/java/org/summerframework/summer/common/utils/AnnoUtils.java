package org.summerframework.summer.common.utils;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author yudong
 * @date 2019/5/18
 */
public class AnnoUtils {

    public static <A extends Annotation> A findAnno(Class<?> clz, Class<A> annoClz) {
        return clz.getAnnotation(annoClz);
    }

    public static <A extends Annotation> boolean existAnno(Class<?> clz, Class<A> annoClz) {
        for (Annotation annotation : clz.getAnnotations()) {
            if (annotation.annotationType() == Target.class
                    || annotation.annotationType() == Retention.class
                    || annotation.annotationType() == Documented.class) {
                continue;
            }
            if (annotation.annotationType() == annoClz) {
                return true;
            }
            return existAnno(annotation.annotationType(), annoClz);
        }
        return false;
    }
}
