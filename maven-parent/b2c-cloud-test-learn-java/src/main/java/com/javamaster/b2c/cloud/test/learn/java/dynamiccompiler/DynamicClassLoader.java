package com.javamaster.b2c.cloud.test.learn.java.dynamiccompiler;

/**
 * Created on 2019/1/25.<br/>
 *
 * @author yudong
 */
public class DynamicClassLoader extends ClassLoader {
    private final byte[] classBytes;

    public DynamicClassLoader(byte[] classBytes) {
        super(DynamicClassLoader.class.getClassLoader());
        this.classBytes = classBytes;
    }

    @Override
    protected Class<?> findClass(String name) {
        return defineClass(name, classBytes, 0, classBytes.length);
    }

    public Class<?> invokeDefineClass(String name) {
        return defineClass(name, classBytes, 0, classBytes.length);
    }

}
