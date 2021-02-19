package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;

import com.javamaster.b2c.cloud.test.learn.java.bytecode.modifier.ClassModifier;
import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantInfo;
import com.google.common.collect.Lists;

import java.io.DataInputStream;
import java.util.List;

/**
 * 描述内部类和宿主类之间的关系
 *
 * @author yudong
 * @date 2019/6/26
 */
public class InnerClassesAttribute extends Attribute {
    private short numberOfClasses;
    private List<InnerClassesInfo> innerClasses = Lists.newArrayList();

    public InnerClassesAttribute(short nameIndex) {
        super(nameIndex);
    }

    @Override
    public void initSubInfo(DataInputStream infoStream) throws Exception {
        numberOfClasses = infoStream.readShort();
        for (int i = 0; i < numberOfClasses; i++) {
            innerClasses.add(new InnerClassesInfo(infoStream.readShort(), infoStream.readShort(),
                    infoStream.readShort(), infoStream.readShort()));
        }
    }

    public short getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(short numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }

    public List<InnerClassesInfo> getInnerClasses() {
        return innerClasses;
    }

    public void setInnerClasses(List<InnerClassesInfo> innerClasses) {
        this.innerClasses = innerClasses;
    }

    private class InnerClassesInfo {
        /**
         * 内部类的符号引用索引下标
         */
        private short innerClassInfoIndex;
        /**
         * 宿主类的符号引用索引下标
         */
        private short outerClassInfoIndex;
        /**
         * 内部类名称索引下标,匿名内部类此项为0
         */
        private short innerNameIndex;
        /**
         * 内部类访问标志
         */
        private short innerClassAccessFlag;

        public InnerClassesInfo(short innerClassInfoIndex, short outerClassInfoIndex, short innerNameIndex, short innerClassAccessFlag) {
            this.innerClassInfoIndex = innerClassInfoIndex;
            this.outerClassInfoIndex = outerClassInfoIndex;
            this.innerNameIndex = innerNameIndex;
            this.innerClassAccessFlag = innerClassAccessFlag;
        }

        public Object getInnerClassInfo() {
            ConstantInfo constantInfo = constantPool.getConstantInfos()[innerClassInfoIndex];
            return constantInfo.getValue();
        }

        public Object getOuterClassInfo() {
            if (outerClassInfoIndex == 0) {
                return "";
            }
            ConstantInfo constantInfo = constantPool.getConstantInfos()[outerClassInfoIndex];
            return constantInfo.getValue();
        }

        public Object getInnerName() {
            if (innerNameIndex == 0) {
                return "";
            }
            ConstantInfo constantInfo = constantPool.getConstantInfos()[innerNameIndex];
            return constantInfo.getValue();
        }

        public List<ClassModifier> getModifiers() {
            return ClassModifier.getModifiers(innerClassAccessFlag);
        }

        public short getInnerClassInfoIndex() {
            return innerClassInfoIndex;
        }

        public void setInnerClassInfoIndex(short innerClassInfoIndex) {
            this.innerClassInfoIndex = innerClassInfoIndex;
        }

        public short getOuterClassInfoIndex() {
            return outerClassInfoIndex;
        }

        public void setOuterClassInfoIndex(short outerClassInfoIndex) {
            this.outerClassInfoIndex = outerClassInfoIndex;
        }

        public short getInnerNameIndex() {
            return innerNameIndex;
        }

        public void setInnerNameIndex(short innerNameIndex) {
            this.innerNameIndex = innerNameIndex;
        }

        public short getInnerClassAccessFlag() {
            return innerClassAccessFlag;
        }

        public void setInnerClassAccessFlag(short innerClassAccessFlag) {
            this.innerClassAccessFlag = innerClassAccessFlag;
        }
    }
}
