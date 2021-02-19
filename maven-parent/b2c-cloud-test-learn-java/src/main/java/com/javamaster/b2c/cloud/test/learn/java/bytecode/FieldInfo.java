package com.javamaster.b2c.cloud.test.learn.java.bytecode;


import com.javamaster.b2c.cloud.test.learn.java.bytecode.access.FieldAccess;
import com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute.AttributeInfo;
import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantUtf8Info;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.DataInputStream;

/**
 * 描述当前类或接口声明的所有字段，但不包括从父类或父接口继承的部分
 *
 * @author yudong
 * @date 2019/1/11
 */
@JsonPropertyOrder({"access", "nameIndex", "name", "descriptorIndex", "descriptor"})
public class FieldInfo {
    private FieldAccess access;
    private short nameIndex;
    /**
     * B  byte        有符号字节型数
     * C  char        Unicode 字符，UTF-16 编码
     * D  double      双精度浮点数
     * F  float       单精度浮点数
     * I  int         整型数
     * J  long        长整数
     * S  short       有符号短整数
     * Z  boolean     布尔值 true/false
     * L  Classname   reference 一个名为<Classname>的实例
     * [  reference   一个一维数组
     */
    private short descriptorIndex;
    private short attributeCount;
    private AttributeInfo attributeInfo;

    @JsonIgnore
    private ConstantPool constantPool;

    public void initFieldInfo(DataInputStream dataInputStream, ConstantPool constantPool) throws Exception {
        access = new FieldAccess(dataInputStream.readShort());
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

    public FieldAccess getAccess() {
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
