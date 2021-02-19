package com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure;

import java.io.DataInputStream;

/**
 * @author yudong
 * @date 2019/6/24
 */
public class ConstantPaddingInfo extends ConstantInfo {
    public ConstantPaddingInfo(byte tag) {
        super(tag);
    }

    @Override
    public void initConstantInfo(DataInputStream dataInputStream) throws Exception {

    }

    @Override
    public Object getValue() {
        return null;
    }
}
