package com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure;

import java.io.DataInputStream;

/**
 * Created on 2019/1/11.<br/>
 *
 * @author yudong
 */
public class ConstantClassInfo extends ConstantInfo {
    private short index;

    public ConstantClassInfo(byte tag) {
        super(tag);
    }

    @Override
    public void initConstantInfo(DataInputStream dataInputStream) throws Exception {
        index = dataInputStream.readShort();
    }

    @Override
    public Object getValue() {
        return getClassValue();
    }

    public String getClassValue() {
        ConstantUtf8Info constantInfo = (ConstantUtf8Info) constantPool.getConstantInfos()[index];
        return constantInfo.getBytesValue();
    }

    public short getIndex() {
        return index;
    }

    public void setIndex(short index) {
        this.index = index;
    }
}
