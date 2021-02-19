package com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure;

import java.io.DataInputStream;

/**
 * Created on 2019/1/11.<br/>
 *
 * @author yudong
 */
public class ConstantStringInfo extends ConstantInfo {
    private short index;

    public ConstantStringInfo(byte tag) {
        super(tag);
    }

    @Override
    public void initConstantInfo(DataInputStream dataInputStream) throws Exception {
        index = dataInputStream.readShort();
    }

    @Override
    public Object getValue() {
        return getStringValue();
    }

    public String getStringValue() {
        ConstantUtf8Info constantUtf8Info = (ConstantUtf8Info) constantPool.getConstantInfos()[index];
        return constantUtf8Info.getBytesValue();
    }

    public short getIndex() {
        return index;
    }

    public void setIndex(short index) {
        this.index = index;
    }
}
