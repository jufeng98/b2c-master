package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;

import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantInfo;
import com.google.common.collect.Lists;

import java.io.DataInputStream;
import java.util.List;

/**
 * 描述栈帧中局部变量表中的变量与源码中定义的变量之间的关系
 *
 * @author yudong
 * @date 2019/6/26
 */
public class LocalVariableTableAttribute extends Attribute {
    private short localVariableTableLength;
    private List<LocalVariableInfo> localVariableTable = Lists.newArrayList();

    public LocalVariableTableAttribute(short nameIndex) {
        super(nameIndex);
    }

    @Override
    public void initSubInfo(DataInputStream infoStream) throws Exception {
        localVariableTableLength = infoStream.readShort();
        for (int i = 0; i < localVariableTableLength; i++) {
            localVariableTable.add(new LocalVariableInfo(infoStream.readShort(), infoStream.readShort(), infoStream.readShort(),
                    infoStream.readShort(), infoStream.readShort()));
        }
    }

    public short getLocalVariableTableLength() {
        return localVariableTableLength;
    }

    public void setLocalVariableTableLength(short localVariableTableLength) {
        this.localVariableTableLength = localVariableTableLength;
    }

    public List<LocalVariableInfo> getLocalVariableTable() {
        return localVariableTable;
    }

    public void setLocalVariableTable(List<LocalVariableInfo> localVariableTable) {
        this.localVariableTable = localVariableTable;
    }

    public class LocalVariableInfo {
        /**
         * 字节码偏移量
         */
        private short startPc;
        /**
         * 作用范围覆盖的长度
         */
        private short length;
        /**
         * 局部变量名称索引下标
         */
        private short nameIndex;
        /**
         * 局部变量描述符索引下标
         */
        private short descriptorIndex;
        /**
         * 局部变量的slot位置
         */
        private short index;

        public LocalVariableInfo(short startPc, short length, short nameIndex, short descriptorIndex, short index) {
            this.startPc = startPc;
            this.length = length;
            this.nameIndex = nameIndex;
            this.descriptorIndex = descriptorIndex;
            this.index = index;
        }

        public Object getName() {
            ConstantInfo constantInfo = constantPool.getConstantInfos()[nameIndex];
            return constantInfo.getValue();
        }

        public Object getDescriptor() {
            ConstantInfo constantInfo = constantPool.getConstantInfos()[descriptorIndex];
            return constantInfo.getValue();
        }

        public short getStartPc() {
            return startPc;
        }

        public void setStartPc(short startPc) {
            this.startPc = startPc;
        }

        public short getLength() {
            return length;
        }

        public void setLength(short length) {
            this.length = length;
        }

        public short getNameIndex() {
            return nameIndex;
        }

        public void setNameIndex(short nameIndex) {
            this.nameIndex = nameIndex;
        }

        public short getDescriptorIndex() {
            return descriptorIndex;
        }

        public void setDescriptorIndex(short descriptorIndex) {
            this.descriptorIndex = descriptorIndex;
        }

        public short getIndex() {
            return index;
        }

        public void setIndex(short index) {
            this.index = index;
        }
    }
}
