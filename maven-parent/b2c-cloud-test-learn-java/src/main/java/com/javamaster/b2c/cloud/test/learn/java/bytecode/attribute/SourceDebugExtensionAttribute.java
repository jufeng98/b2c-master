package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;

import java.io.DataInputStream;

/**
 * 描述额外的调试信息,例如jsp文件的调试(1.6)
 *
 * @author yudong
 * @date 2019/6/26
 */
public class SourceDebugExtensionAttribute extends Attribute {
    public SourceDebugExtensionAttribute(short nameIndex) {
        super(nameIndex);
    }

    @Override
    public void initSubInfo(DataInputStream infoStream) throws Exception {

    }
}
