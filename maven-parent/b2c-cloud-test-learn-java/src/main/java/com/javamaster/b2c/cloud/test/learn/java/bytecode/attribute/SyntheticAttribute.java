package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;

import java.io.DataInputStream;

/**
 * 描述类,字段或方法是由编译器自行产生的
 *
 * @author yudong
 * @date 2019/6/26
 */
public class SyntheticAttribute extends Attribute {
    public SyntheticAttribute(short nameIndex) {
        super(nameIndex);
    }

    @Override
    public void initSubInfo(DataInputStream infoStream) throws Exception {

    }
}
