package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;

import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantClassInfo;
import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantUtf8Info;
import com.google.common.collect.Lists;

import java.io.DataInputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述方法可能抛出的异常,也就是throws关键字后面的异常
 *
 * @author yudong
 * @date 2019/6/26
 */
public class ExceptionsAttribute extends Attribute {
    private short numberOfExceptions;
    private List<Short> exceptionIndexTable = Lists.newArrayList();

    public ExceptionsAttribute(short nameIndex) {
        super(nameIndex);
    }

    @Override
    public void initSubInfo(DataInputStream infoStream) throws Exception {
        numberOfExceptions = infoStream.readShort();
        for (int i = 0; i < numberOfExceptions; i++) {
            exceptionIndexTable.add(infoStream.readShort());
        }
    }

    public List<String> getExceptionIndexTableNames() {
        return exceptionIndexTable.stream()
                .map(exceptionIndex -> {
                    ConstantClassInfo constantClassInfo = (ConstantClassInfo) constantPool.getConstantInfos()[exceptionIndex];
                    ConstantUtf8Info constantUtf8Info = (ConstantUtf8Info) constantPool.getConstantInfos()[constantClassInfo.getIndex()];
                    return constantUtf8Info.getBytesValue();
                })
                .collect(Collectors.toList());
    }

    public short getNumberOfExceptions() {
        return numberOfExceptions;
    }

    public void setNumberOfExceptions(short numberOfExceptions) {
        this.numberOfExceptions = numberOfExceptions;
    }

    public List<Short> getExceptionIndexTable() {
        return exceptionIndexTable;
    }

    public void setExceptionIndexTable(List<Short> exceptionIndexTable) {
        this.exceptionIndexTable = exceptionIndexTable;
    }
}
