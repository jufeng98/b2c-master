package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;


import com.javamaster.b2c.cloud.test.learn.java.bytecode.ConstantPool;
import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantUtf8Info;

import java.io.DataInputStream;

/**
 * @author yudong
 * @date 2019/1/11
 */
public class AttributeInfo {
    private final Attribute[] attributes;

    public AttributeInfo(short count) {
        attributes = new Attribute[count];
    }

    public void initAttributeInfo(DataInputStream dataInputStream, ConstantPool constantPool) throws Exception {
        for (int i = 0; i < attributes.length; i++) {
            Attribute attribute;
            short nameIndex = dataInputStream.readShort();
            String name = ((ConstantUtf8Info) constantPool.getConstantInfos()[nameIndex]).getBytesValue();
            Class<? extends Attribute> attributeTypeClz = AttributeTypeEnum.getAttributeType(name);
            attribute = attributeTypeClz.getDeclaredConstructor(short.class).newInstance(nameIndex);
            attribute.initAttribute(dataInputStream, constantPool);
            attribute.setConstantPool(constantPool);
            attributes[i] = attribute;
        }
    }

    public Attribute[] getAttributes() {
        return attributes;
    }
}
