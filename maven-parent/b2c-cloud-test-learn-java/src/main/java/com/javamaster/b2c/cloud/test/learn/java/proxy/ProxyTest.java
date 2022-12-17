package com.javamaster.b2c.cloud.test.learn.java.proxy;

import lombok.SneakyThrows;
import org.junit.Test;

import java.lang.reflect.*;
import java.util.Arrays;

/**
 * @author yudong
 * @date 2022/11/15
 */
public class ProxyTest {
    @Test
    @SneakyThrows
    @SuppressWarnings("ALL")
    public void test6() {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(proxy.getClass());
                System.out.println(method);
                System.out.println(Arrays.toString(args));
                return "hello";
            }
        };
        MyInterface proxy = (MyInterface) Proxy.newProxyInstance(
                MyInterface.class.getClassLoader(),
                new Class[]{MyInterface.class},
                handler);
        System.out.println(proxy.getClass());
        System.out.println(proxy.getDesc("yu", 22));
    }


    public interface MyInterface {
        String getDesc(String name, int age);
    }
}
