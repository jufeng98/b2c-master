package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;

import com.javamaster.b2c.cloud.test.learn.java.bytecode.ConstantPool;
import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantUtf8Info;
import com.javamaster.b2c.cloud.test.learn.java.json.ByteArraySerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/**
 * @author yudong
 * @date 2019/1/11
 */
@JsonPropertyOrder({"attributeNameIndex", "attributeName"})
public abstract class Attribute {
    protected short attributeNameIndex;
    protected int attributeLength;
    @JsonSerialize(using = ByteArraySerializer.class)
    protected byte[] info;

    @JsonIgnore
    protected ConstantPool constantPool;

    public Attribute(short nameIndex) {
        this.attributeNameIndex = nameIndex;
    }

    public void initAttribute(DataInputStream dataInputStream, ConstantPool constantPool) throws Exception {
        attributeLength = dataInputStream.readInt();
        info = new byte[attributeLength];
        this.constantPool = constantPool;
        if (attributeLength > 0) {
            dataInputStream.readFully(info);
        }
        if (attributeLength == 0) {
            return;
        }
        try (DataInputStream stream = new DataInputStream(new ByteArrayInputStream(info))) {
            initSubInfo(stream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 初始化info数组代表的意义
     *
     * @param infoStream
     * @throws Exception
     */
    public abstract void initSubInfo(DataInputStream infoStream) throws Exception;


    public String getAttributeName() {
        ConstantUtf8Info constantInfo = ((ConstantUtf8Info) constantPool.getConstantInfos()[attributeNameIndex]);
        return constantInfo.getBytesValue();
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public short getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public void setAttributeNameIndex(short attributeNameIndex) {
        this.attributeNameIndex = attributeNameIndex;
    }

    public int getAttributeLength() {
        return attributeLength;
    }

    public void setAttributeLength(int attributeLength) {
        this.attributeLength = attributeLength;
    }

    public byte[] getInfo() {
        return info;
    }

    public void setInfo(byte[] info) {
        this.info = info;
    }
}
