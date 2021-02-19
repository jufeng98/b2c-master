package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;

import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantInfo;

import java.io.DataInputStream;

/**
 * 描述生成这个class文件的源码名称
 *
 * @author yudong
 * @date 2019/6/26
 */
public class SourceFileAttribute extends Attribute {
    private short sourceFileIndex;

    public SourceFileAttribute(short nameIndex) {
        super(nameIndex);
    }

    @Override
    public void initSubInfo(DataInputStream infoStream) throws Exception {
        sourceFileIndex = infoStream.readShort();
    }

    public Object getSourceFileName() {
        ConstantInfo constantInfo = constantPool.getConstantInfos()[sourceFileIndex];
        return constantInfo.getValue();
    }

    public short getSourceFileIndex() {
        return sourceFileIndex;
    }

    public void setSourceFileIndex(short sourceFileIndex) {
        this.sourceFileIndex = sourceFileIndex;
    }
}
