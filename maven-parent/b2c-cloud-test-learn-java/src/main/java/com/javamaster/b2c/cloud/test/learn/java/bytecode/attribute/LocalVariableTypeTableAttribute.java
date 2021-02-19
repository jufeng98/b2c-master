package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;

import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantInfo;
import com.google.common.collect.Lists;

import java.io.DataInputStream;
import java.util.List;

/**
 * 描述泛型参数化类型(1.5)
 *
 * @author yudong
 * @date 2019/6/26
 */
public class LocalVariableTypeTableAttribute extends Attribute {
    private short localVariableTypeTableLength;
    private List<LocalVariableTypeTable> localVariableTypeTable = Lists.newArrayList();

    public LocalVariableTypeTableAttribute(short nameIndex) {
        super(nameIndex);
    }

    @Override
    public void initSubInfo(DataInputStream infoStream) throws Exception {
        localVariableTypeTableLength = infoStream.readShort();
        for (int i = 0; i < localVariableTypeTableLength; i++) {
            localVariableTypeTable.add(new LocalVariableTypeTable(infoStream.readShort(), infoStream.readShort(),
                    infoStream.readShort(), infoStream.readShort(), infoStream.readShort()));
        }
    }

    public short getLocalVariableTypeTableLength() {
        return localVariableTypeTableLength;
    }

    public void setLocalVariableTypeTableLength(short localVariableTypeTableLength) {
        this.localVariableTypeTableLength = localVariableTypeTableLength;
    }

    public List<LocalVariableTypeTable> getLocalVariableTypeTable() {
        return localVariableTypeTable;
    }

    public void setLocalVariableTypeTable(List<LocalVariableTypeTable> localVariableTypeTable) {
        this.localVariableTypeTable = localVariableTypeTable;
    }

    public class LocalVariableTypeTable {
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
        private short signatureIndex;
        /**
         * 局部变量的slot位置
         */
        private short index;

        public LocalVariableTypeTable(short startPc, short length, short nameIndex, short signatureIndex, short index) {
            this.startPc = startPc;
            this.length = length;
            this.nameIndex = nameIndex;
            this.signatureIndex = signatureIndex;
            this.index = index;
        }

        public Object getName() {
            ConstantInfo constantInfo = constantPool.getConstantInfos()[nameIndex];
            return constantInfo.getValue();
        }

        public Object getSignature() {
            ConstantInfo constantInfo = constantPool.getConstantInfos()[signatureIndex];
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

        public short getSignatureIndex() {
            return signatureIndex;
        }

        public void setSignatureIndex(short signatureIndex) {
            this.signatureIndex = signatureIndex;
        }

        public short getIndex() {
            return index;
        }

        public void setIndex(short index) {
            this.index = index;
        }

    }
}
