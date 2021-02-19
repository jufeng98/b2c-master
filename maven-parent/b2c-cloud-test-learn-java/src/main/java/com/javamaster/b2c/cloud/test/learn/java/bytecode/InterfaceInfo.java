package com.javamaster.b2c.cloud.test.learn.java.bytecode;


import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantClassInfo;
import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantUtf8Info;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/1/11.<br/>
 *
 * @author yudong
 */
public class InterfaceInfo {
    private final Short[] interfaceIndexes;

    @JsonIgnore
    private ConstantPool constantPool;

    public InterfaceInfo(short interfaceCount) {
        interfaceIndexes = new Short[interfaceCount];
    }

    public List<String> getInterfaceNames() {
        List<String> list = new ArrayList<>(interfaceIndexes.length);
        for (Short interfaceIndex : interfaceIndexes) {
            ConstantClassInfo constantInfo = ((ConstantClassInfo) constantPool.getConstantInfos()[interfaceIndex]);
            ConstantUtf8Info constantUtf8Info = (ConstantUtf8Info) constantPool.getConstantInfos()[constantInfo.getIndex()];
            String name = constantUtf8Info.getBytesValue();
            list.add(name);
        }
        return list;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public Short[] getInterfaceIndexes() {
        return interfaceIndexes;
    }
}
