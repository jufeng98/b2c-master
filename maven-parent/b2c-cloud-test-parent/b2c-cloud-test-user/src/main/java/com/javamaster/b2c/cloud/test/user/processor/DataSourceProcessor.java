package com.javamaster.b2c.cloud.test.user.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.sql.Connection;

/**
 * Created on 2019/1/13.<br/>
 *
 * @author yudong
 */
// @Component
public class DataSourceProcessor implements BeanPostProcessor {

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DataSource) {
            DataSource dataSource = (DataSource) bean;
            Type[] types = dataSource.getClass().getGenericInterfaces();
            Class[] classes = ((Class[]) types);
            Object proxyInstance = Proxy.newProxyInstance(bean.getClass().getClassLoader(), classes, (Object p, Method m, Object[] args) -> {
                if (m.getName().equals("getConnection")) {
                    Connection conn = threadLocal.get();
                    if (conn == null) {
                        conn = dataSource.getConnection();
                        threadLocal.set(conn);
                    }
                    return conn;
                }
                return m.invoke(bean, args);
            });
            return proxyInstance;
        }
        return bean;
    }
}
