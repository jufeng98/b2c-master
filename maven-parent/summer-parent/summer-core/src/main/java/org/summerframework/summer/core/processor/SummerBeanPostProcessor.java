package org.summerframework.summer.core.processor;

/**
 * @author yudong
 */
public interface SummerBeanPostProcessor {

    default Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    default Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }

}
