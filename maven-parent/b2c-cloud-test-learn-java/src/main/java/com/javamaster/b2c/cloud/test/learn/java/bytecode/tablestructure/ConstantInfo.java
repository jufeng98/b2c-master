package com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure;

import com.javamaster.b2c.cloud.test.learn.java.bytecode.ConstantPool;
import com.javamaster.b2c.cloud.test.learn.java.jackson.TagSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.DataInputStream;

/**
 * @author yudong
 * @date 2019/1/11
 */
public abstract class ConstantInfo {
    @JsonSerialize(using = TagSerializer.class)
    private final byte tag;

    @JsonIgnore
    protected ConstantPool constantPool;

    public ConstantInfo(byte tag) {
        this.tag = tag;
    }

    /**
     * 初始化
     *
     * @param dataInputStream
     * @throws Exception
     */
    public abstract void initConstantInfo(DataInputStream dataInputStream) throws Exception;

    /**
     * 获取信息
     *
     * @return
     */
    public abstract Object getValue();

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public static ConstantInfo getConstantInfo(byte tag) {
        return ConstantTypeEnum.getConstantType(tag).newInstance();
    }

    public byte getTag() {
        return tag;
    }

}
