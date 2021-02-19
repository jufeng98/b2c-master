package com.javamaster.b2c.cloud.test.learn.java.thinking;

import com.javamaster.b2c.cloud.test.learn.java.test.PdfTest;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @author yu
 */
@Slf4j
public class UnitTestHandler {
    public static void handleTestAnnotation(Class<?> class1) throws Exception {
        Object instance = class1.newInstance();
        Method[] methods = class1.getDeclaredMethods();
        AccessibleObject.setAccessible(methods, true);
        for (Method method : methods) {
            if (method.getAnnotation(UnitTest.class) != null) {
                log.info("开始执行单元测试方法:{}",method.getName());
                method.invoke(instance);
                log.info("单元测试方法执行完成:{}",method.getName());
            }
        }
    }
}

class UnitTestHandlerTest {
    public static void main(String[] args) throws Exception {
        UnitTestHandler.handleTestAnnotation(PdfTest.class);
    }
}