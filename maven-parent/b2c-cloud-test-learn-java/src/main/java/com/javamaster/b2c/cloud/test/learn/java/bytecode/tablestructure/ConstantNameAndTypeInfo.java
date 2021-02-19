package com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure;

import java.io.DataInputStream;

/**
 * Created on 2019/1/11.<br/>
 *
 * @author yudong
 */
public class ConstantNameAndTypeInfo extends ConstantInfo {
    private short fieldOrMethodNameIndex;
    private short fieldOrMethodDescIndex;

    public ConstantNameAndTypeInfo(byte tag) {
        super(tag);
    }

    @Override
    public void initConstantInfo(DataInputStream dataInputStream) throws Exception {
        fieldOrMethodNameIndex = dataInputStream.readShort();
        fieldOrMethodDescIndex = dataInputStream.readShort();
    }

    @Override
    public Object getValue() {
        return getFieldOrMethodName() + ":" + getFieldOrMethodDesc();
    }


    public String getFieldOrMethodName() {
        ConstantUtf8Info constantUtf8Info = (ConstantUtf8Info) constantPool.getConstantInfos()[fieldOrMethodNameIndex];
        return constantUtf8Info.getBytesValue();
    }

    public String getFieldOrMethodDesc() {
        ConstantUtf8Info constantUtf8Info = (ConstantUtf8Info) constantPool.getConstantInfos()[fieldOrMethodDescIndex];
        return constantUtf8Info.getBytesValue();
    }

    public short getFieldOrMethodNameIndex() {
        return fieldOrMethodNameIndex;
    }

    public void setFieldOrMethodNameIndex(short fieldOrMethodNameIndex) {
        this.fieldOrMethodNameIndex = fieldOrMethodNameIndex;
    }

    public short getFieldOrMethodDescIndex() {
        return fieldOrMethodDescIndex;
    }

    public void setFieldOrMethodDescIndex(short fieldOrMethodDescIndex) {
        this.fieldOrMethodDescIndex = fieldOrMethodDescIndex;
    }
}
