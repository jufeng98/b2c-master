package com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure;

import java.io.DataInputStream;

/**
 * Created on 2019/1/11.<br/>
 *
 * @author yudong
 */
public class ConstantFieldRefInfo extends ConstantInfo {
    private short classOrInterfaceIndex;
    private short nameAndTypeIndex;

    public ConstantFieldRefInfo(byte tag) {
        super(tag);
    }

    @Override
    public void initConstantInfo(DataInputStream dataInputStream) throws Exception {
        classOrInterfaceIndex = dataInputStream.readShort();
        nameAndTypeIndex = dataInputStream.readShort();
    }

    @Override
    public Object getValue() {
        return getClassOfInterfaceName() + ":" + getNameAndTypeIndexName();
    }

    public String getClassOfInterfaceName() {
        ConstantClassInfo constantInfo = (ConstantClassInfo) constantPool.getConstantInfos()[classOrInterfaceIndex];
        ConstantUtf8Info constantUtf8Info = (ConstantUtf8Info) constantPool.getConstantInfos()[constantInfo.getIndex()];
        return constantUtf8Info.getBytesValue();
    }

    public String getNameAndTypeIndexName() {
        ConstantNameAndTypeInfo constantInfo = (ConstantNameAndTypeInfo) constantPool.getConstantInfos()[nameAndTypeIndex];
        ConstantUtf8Info constantUtf8Info = (ConstantUtf8Info) constantPool.getConstantInfos()[constantInfo.getFieldOrMethodNameIndex()];
        ConstantUtf8Info constantUtf8Info1 = (ConstantUtf8Info) constantPool.getConstantInfos()[constantInfo.getFieldOrMethodDescIndex()];
        return constantUtf8Info.getBytesValue() + ":" + constantUtf8Info1.getBytesValue();
    }

    public short getClassOrInterfaceIndex() {
        return classOrInterfaceIndex;
    }


    public short getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

}
