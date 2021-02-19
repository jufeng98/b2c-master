package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;

import java.io.DataInputStream;

/**
 * 描述类,方法等被声明废弃
 *
 * @author yudong
 * @date 2019/6/26
 */
public class DeprecatedAttribute extends Attribute {
    public DeprecatedAttribute(short nameIndex) {
        super(nameIndex);
    }

    @Override
    public void initSubInfo(DataInputStream infoStream) throws Exception {

    }
}
