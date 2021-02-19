package com.javamaster.b2c.cloud.test.learn.java.virtualmachine;

/**
 * Created on 2018/9/10.<br/>
 *
 * @author yudong
 */
public class HotSwapClassLoader extends ClassLoader {
    /**
     * 能够访问服务端引用类库的关键
     */
    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classBytes) {
        return defineClass(null, classBytes, 0, classBytes.length);
    }
}
