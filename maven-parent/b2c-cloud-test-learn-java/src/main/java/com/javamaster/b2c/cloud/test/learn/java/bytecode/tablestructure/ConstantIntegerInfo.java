package com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure;

import java.io.DataInputStream;

/**
 * Created on 2019/1/11.<br/>
 *
 * @author yudong
 */
public class ConstantIntegerInfo extends ConstantInfo {
    private int bytes;

    public ConstantIntegerInfo(byte tag) {
        super(tag);
    }

    @Override
    public void initConstantInfo(DataInputStream dataInputStream) throws Exception {
        bytes = dataInputStream.readInt();
    }

    @Override
    public Object getValue() {
        return getBytes();
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }
}
