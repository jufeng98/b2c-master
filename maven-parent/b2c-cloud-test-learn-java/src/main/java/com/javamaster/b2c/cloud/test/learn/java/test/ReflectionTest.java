package com.javamaster.b2c.cloud.test.learn.java.test;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author yudong
 * @date 2019/5/9
 */
@Slf4j
public class ReflectionTest {

    @Test
    @SneakyThrows
    public void test() {
        Method method = MyClass.class.getMethod("getStringList");
        Type returnType = method.getGenericReturnType();
        if (returnType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) returnType;
            Type[] typeArguments = type.getActualTypeArguments();
            for (Type typeArgument : typeArguments) {
                Class<?> typeArgClass = (Class<?>) typeArgument;
                System.out.println("typeArgClass = " + typeArgClass);
            }
        }
    }

    @Test
    @SneakyThrows
    public void test1() {
        Method method = MyClass.class.getMethod("setStringList", List.class);
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        for (Type genericParameterType : genericParameterTypes) {
            if (genericParameterType instanceof ParameterizedType) {
                ParameterizedType aType = (ParameterizedType) genericParameterType;
                Type[] parameterArgTypes = aType.getActualTypeArguments();
                for (Type parameterArgType : parameterArgTypes) {
                    Class<?> parameterArgClass = (Class<?>) parameterArgType;
                    System.out.println("parameterArgClass = " + parameterArgClass);
                }
            }
        }
    }

    @Test
    @SneakyThrows
    public void test3() {
        Field field = MyClass.class.getDeclaredField("stringList");
        Type genericFieldType = field.getGenericType();
        if (genericFieldType instanceof ParameterizedType) {
            ParameterizedType aType = (ParameterizedType) genericFieldType;
            Type[] fieldArgTypes = aType.getActualTypeArguments();
            for (Type fieldArgType : fieldArgTypes) {
                Class<?> fieldArgClass = (Class<?>) fieldArgType;
                System.out.println("fieldArgClass = " + fieldArgClass);
            }
        }
    }

    @Test
    @SneakyThrows
    public void test4() {
        int[] intArray = (int[]) Array.newInstance(int.class, 3);
        Array.set(intArray, 0, 123);
        Array.set(intArray, 1, 456);
        Array.set(intArray, 2, 789);

        System.out.println("intArray[0] = " + Array.get(intArray, 0));
        System.out.println("intArray[1] = " + Array.get(intArray, 1));
        System.out.println("intArray[2] = " + Array.get(intArray, 2));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("ALL")
    public void test5() {
        Class<String[]> stringArrayClass = String[].class;
        Class<int[]> intArray = (Class<int[]>) Class.forName("[I");
        Class<String[]> stringArrayClass1 = (Class<String[]>) Class.forName("[Ljava.lang.String;");
        Class<Integer> integerClass = int.class;

        String[] strings = new String[3];
        stringArrayClass = (Class<String[]>) strings.getClass();
        Class<?> stringArrayComponentType = stringArrayClass.getComponentType();
        System.out.println(stringArrayComponentType);
    }

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

    public static class MyClass {
        protected List<String> stringList = Lists.newArrayList();

        public List<String> getStringList() {
            return this.stringList;
        }

        public void setStringList(List<String> list) {
            this.stringList = list;
        }
    }

    public interface MyInterface {
        String getDesc(String name, int age);
    }
}


