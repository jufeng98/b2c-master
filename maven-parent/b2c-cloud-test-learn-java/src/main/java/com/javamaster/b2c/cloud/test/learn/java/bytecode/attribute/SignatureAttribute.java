package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;

import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantInfo;

import java.io.DataInputStream;

/**
 * 描述类,方法,字段的特征签名,为了支持泛型.因为类型擦除之后,描述符无法再描述泛型信息(1.5)
 * attributeLength的长度固定为2
 *
 * @author yudong
 * @date 2019/6/26
 */
public class SignatureAttribute extends Attribute {
    private short signatureIndex;

    public SignatureAttribute(short nameIndex) {
        super(nameIndex);
    }

    @Override
    public void initSubInfo(DataInputStream infoStream) throws Exception {
        signatureIndex = infoStream.readShort();
    }

    public Object getSignatureValue() {
        ConstantInfo constantInfo = constantPool.getConstantInfos()[signatureIndex];
        return constantInfo.getValue();
    }
}
