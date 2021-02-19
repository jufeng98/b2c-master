package org.summerframework.summer.core.ioc;

import java.util.List;

/**
 * @author yudong
 */
public interface SummerApplicationContext {

    Object getBean(String beanName);

    <T> T getBean(Class<T> clz);

    <T> List<T> getBeanOfType(Class<T> clz);

    Object getObject(String key);

    void saveObject(String key, Object object);

}
