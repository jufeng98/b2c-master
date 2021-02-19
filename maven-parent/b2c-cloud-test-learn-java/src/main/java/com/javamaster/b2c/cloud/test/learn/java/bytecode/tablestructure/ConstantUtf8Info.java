package com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure;

import com.javamaster.b2c.cloud.test.learn.java.bytecode.utils.StrUtils;

import java.io.DataInputStream;

/**
 * Created on 2019/1/11.<br/>
 *
 * @author yudong
 */
public class ConstantUtf8Info extends ConstantInfo {
    private short length;
    private byte[] bytes;

    public ConstantUtf8Info(byte tag) {
        super(tag);
    }

    @Override
    public void initConstantInfo(DataInputStream dataInputStream) throws Exception {
        length = dataInputStream.readShort();
        bytes = new byte[length];
        dataInputStream.read(bytes);
    }

    @Override
    public Object getValue() {
        return getBytesValue();
    }

    public String getBytesValue() {
        return StrUtils.getStringValue(bytes);
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

}
