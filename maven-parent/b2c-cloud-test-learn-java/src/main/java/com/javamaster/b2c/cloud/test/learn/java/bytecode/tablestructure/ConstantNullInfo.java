package com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure;

import java.io.DataInputStream;

/**
 * Created on 2019/1/11.<br/>
 *
 * @author yudong
 */
public class ConstantNullInfo extends ConstantInfo {

    public ConstantNullInfo(byte tag) {
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
