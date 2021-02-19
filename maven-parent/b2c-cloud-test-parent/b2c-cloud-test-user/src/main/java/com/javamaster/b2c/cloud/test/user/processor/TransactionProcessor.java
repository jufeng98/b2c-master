package com.javamaster.b2c.cloud.test.user.processor;

import com.javamaster.b2c.cloud.test.user.annotation.Transaction;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;

import javax.sql.DataSource;
import java.lang.reflect.*;
import java.sql.Connection;

/**
 * Created on 2019/1/13.<br/>
 *
 * @author yudong
 */
// @Component
public class TransactionProcessor implements BeanPostProcessor {

    @Autowired
    private ApplicationContext context;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Method[] methods = bean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            Transaction annotation = AnnotationUtils.findAnnotation(method, Transaction.class);
            if (annotation != null) {
                Type[] types = bean.getClass().getGenericInterfaces();
                Class[] classes = ((Class[]) types);
                Object proxyInstance = Proxy.newProxyInstance(bean.getClass().getClassLoader(), classes, (Object p, Method m, Object[] args) -> {
                    DataSource dataSource = context.getBean(DataSource.class);
                    Connection conn = dataSource.getConnection();
                    boolean autoCommit = conn.getAutoCommit();
                    try {
                        conn.setAutoCommit(false);
                        Object o = m.invoke(bean, args);
                        conn.commit();
                        return o;
                    } catch (Exception e) {
                        Class<? extends Exception> exceptionClass = annotation.rollbackFor();
                        Class targetClass = e.getCause().getClass();
                        if (exceptionClass.isAssignableFrom(targetClass)) {
                            conn.rollback();
                        } else {
                            conn.commit();
                        }
                        throw new RuntimeException("rollback operation", e);
                    } finally {
                        conn.setAutoCommit(autoCommit);
                    }
                });
                return proxyInstance;
            }
        }
        return bean;
    }
}
