package com.javamaster.b2c.cloud.test.learn.java.bytecode;


import com.javamaster.b2c.cloud.test.learn.java.bytecode.access.MethodAccess;
import com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute.AttributeInfo;
import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantUtf8Info;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.DataInputStream;

/**
 * 只描述当前类或接口中声明的方法，不包括从父类或父接口继承的方法
 *
 * @author yudong
 * @date 2019/1/11
 */
@JsonPropertyOrder({"access", "nameIndex", "name", "descriptorIndex", "descriptor"})
public class MethodInfo {
    private MethodAccess access;
    private short nameIndex;
    /**
     * 方法: Object invoke(int i, double d, Thread t)
     * <p>
     * 描述符:(IDLjava/lang/Thread;)Ljava/lang/Object;
     */
    private short descriptorIndex;
    private short attributeCount;
    private AttributeInfo attributeInfo;

    @JsonIgnore
    private ConstantPool constantPool;

    public void initMethodInfo(DataInputStream dataInputStream, ConstantPool constantPool) throws Exception {
        access = new MethodAccess(dataInputStream.readShort());
        nameIndex = dataInputStream.readShort();
        descriptorIndex = dataInputStream.readShort();
        attributeCount = dataInputStream.readShort();
        attributeInfo = new AttributeInfo(attributeCount);
        attributeInfo.initAttributeInfo(dataInputStream, constantPool);
    }


    public String getName() {
        ConstantUtf8Info constantInfo = ((ConstantUtf8Info) constantPool.getConstantInfos()[nameIndex]);
        return constantInfo.getBytesValue();
    }

    public String getDescriptor() {
        ConstantUtf8Info constantInfo = ((ConstantUtf8Info) constantPool.getConstantInfos()[descriptorIndex]);
        return constantInfo.getBytesValue();
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public MethodAccess getAccess() {
        return access;
    }

    public short getNameIndex() {
        return nameIndex;
    }

    public short getDescriptorIndex() {
        return descriptorIndex;
    }

    public short getAttributeCount() {
        return attributeCount;
    }

    public AttributeInfo getAttributeInfo() {
        return attributeInfo;
    }

}
