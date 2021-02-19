package com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure;

import java.io.DataInputStream;

/**
 * Created on 2019/1/11.<br/>
 *
 * @author yudong
 */
public class ConstantMethodTypeInfo extends ConstantInfo {
    private short descriptorIndex;

    public ConstantMethodTypeInfo(byte tag) {
        super(tag);
    }

    @Override
    public void initConstantInfo(DataInputStream dataInputStream) throws Exception {
        descriptorIndex = dataInputStream.readShort();
    }

    @Override
    public Object getValue() {
        return getDescriptor();
    }

    public String getDescriptor() {
        ConstantUtf8Info constantUtf8Info = (ConstantUtf8Info) constantPool.getConstantInfos()[descriptorIndex];
        return constantUtf8Info.getBytesValue();
    }

    public short getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(short descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }
}
