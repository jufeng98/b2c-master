package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;

import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantInfo;

import java.io.DataInputStream;

/**
 * 描述一个类为局部类或匿名类时的访问范围(1.5)
 *
 * @author yudong
 * @date 2019/6/26
 */
public class EnclosingMethodAttribute extends Attribute {
    private short classIndex;
    private short methodIndex;

    public EnclosingMethodAttribute(short nameIndex) {
        super(nameIndex);
    }

    @Override
    public void initSubInfo(DataInputStream infoStream) throws Exception {
        classIndex = infoStream.readShort();
        methodIndex = infoStream.readShort();
    }

    public Object getClassName() {
        ConstantInfo constantInfo = constantPool.getConstantInfos()[classIndex];
        return constantInfo.getValue();
    }

    public Object getMethodInfo() {
        ConstantInfo constantInfo = constantPool.getConstantInfos()[methodIndex];
        return constantInfo.getValue();
    }

}
