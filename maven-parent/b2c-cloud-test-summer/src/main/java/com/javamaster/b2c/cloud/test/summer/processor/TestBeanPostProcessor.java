package com.javamaster.b2c.cloud.test.summer.processor;

import com.javamaster.b2c.cloud.test.summer.helper.HelloHelper;
import com.javamaster.b2c.cloud.test.summer.service.HelloService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.summerframework.summer.core.anno.SummerComponent;
import org.summerframework.summer.core.processor.SummerBeanPostProcessor;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.logging.Logger;

/**
 * @author yudong
 */
@SummerComponent
public class TestBeanPostProcessor implements SummerBeanPostProcessor {
    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        logger.info("before:" + bean.getClass());
        if (bean instanceof HelloService) {
            HelloService helloService = (HelloService) bean;
            Type[] types = helloService.getClass().getGenericInterfaces();
            Class[] classes = ((Class[]) types);
            Object proxyInstance = Proxy
                    .newProxyInstance(bean.getClass().getClassLoader(), classes, (Object proxy, Method m, Object[] args) -> {
                        if (!m.getName().equals("sayHello")) {
                            return m.invoke(bean, args);
                        }
                        logger.info("before postProcessBeforeInitialization intercept:" + bean.getClass());
                        Object object = m.invoke(bean, args);
                        logger.info("after postProcessBeforeInitialization intercept:" + bean.getClass());
                        return object;
                    });
            return proxyInstance;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        logger.info("after:" + bean.getClass());
        if (bean instanceof HelloHelper) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(bean.getClass());
            enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
                logger.info("before postProcessBeforeInitialization intercept:" + bean.getClass());
                Object object = proxy.invokeSuper(obj, args);
                logger.info("before postProcessAfterInitialization intercept:" + bean.getClass());
                return object;
            });
            Object proxyInstance = enhancer.create();
            return proxyInstance;
        }
        return bean;
    }
}
