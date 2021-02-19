package com.javamaster.b2c.cloud.test.learn.java.dynamiccompiler;

import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;

/**
 * Created on 2019/1/25.<br/>
 *
 * @author yudong
 */
public class ByteArrayJavaClass extends SimpleJavaFileObject {
    private ByteArrayOutputStream stream;

    public ByteArrayJavaClass(String name) {
        super(java.net.URI.create("bytes:///" + name), JavaFileObject.Kind.CLASS);
        stream = new java.io.ByteArrayOutputStream();
    }

    @Override
    public java.io.OutputStream openOutputStream() {
        return stream;
    }

    public byte[] getbytes() {
        return stream.toByteArray();
    }
}
