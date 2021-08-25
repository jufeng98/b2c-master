package com.javamaster.b2c.cloud.test.learn.java.jni;

/**
 * src/main目录下执行javah -d jni -classpath .\java com.javamaster.b2c.cloud.test.learn.java.jni.HelloNative
 * 生成.h的头文件
 * <p>
 * 使用MinGW编写.c文件
 * <p>
 * gcc -fpic -c HelloNative.c
 * gcc -shared -fpic -o HelloNative.dll HelloNative.o
 * gcc -o HelloNative.exe HelloNative.c HelloNative.dll
 * <p>
 * gcc -D __int64="long long" -I D:\java\64\jdk1.8.0_131\include -I D:\java\64\jdk1.8.0_131\include/win32 -shared -o HelloNative.dll HelloNative.c
 * <p>
 * gcc -shared HelloNative.c -o HelloNative.dll
 * <p>
 * -Djava.library.path=D:\my_opensource_project\b2c-master\maven-parent\b2c-cloud-test-learn-java\src\main\java\com\javamaster\b2c\cloud\test\learn\java\jni
 *
 * @author yudong
 * @date 2021/8/13
 */
public class HelloNative {

    public static native void greeting();

}
