package com.javamaster.b2c.cloud.test.learn.java.bytecode;


import com.javamaster.b2c.cloud.test.learn.java.bytecode.access.ClassAccess;
import com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute.AttributeInfo;
import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantDoubleInfo;
import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantInfo;
import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantLongInfo;
import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantPaddingInfo;
import com.javamaster.b2c.cloud.test.learn.java.json.HexSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created on 2019/1/11.<br/>
 *
 * @author yudong
 */
public class ClassFileReader {
    /**
     * 魔数，魔数的唯一作用是确定这个文件是否为一个能被虚拟机所接受的 Class 文件。魔
     * 数值固定为 0xCAFEBABE，不会改变
     */
    @JsonSerialize(using = HexSerializer.class)
    private int magic;
    /**
     * 副版本号
     */
    private short minorVersion;
    /**
     * 主版本号
     * 52:1.8
     */
    private short majorVersion;
    /**
     * 常量池数量
     */
    private short constantPoolCount;

    private ConstantPool constantPool;

    private ClassAccess classAccess;
    /**
     * 类索引
     */
    private short thisClass;
    /**
     * 父类索引
     * <p>
     * 如果 Class 文件的 super_class 的值为 0，那这个 Class 文件只可能是定义的是
     * java.lang.Object 类，只有它是唯一没有父类的类
     */
    private short superClass;
    /**
     * 接口数量
     */
    private short interfaceCount;
    /**
     * 接口表
     */
    private InterfaceInfo interfaceInfo;
    /**
     * 字段数量
     */
    private short fieldCount;
    /**
     * 字段表
     */
    private FieldInfo[] fieldInfos;
    /**
     * 方法数量
     */
    private short methodCount;
    /**
     * 方法表
     */
    private MethodInfo[] methodInfos;
    /**
     * 属性数量
     */
    private short attributeCount;
    /**
     * 属性表
     */
    private AttributeInfo attributeInfo;


    public ClassFileReader(InputStream classFileStream) {
        initClassFileInfo(classFileStream);
    }

    public String getClassName() {
        ConstantInfo constantInfo = constantPool.getConstantInfos()[thisClass];
        String name = (String) constantInfo.getValue();
        return name.replace("/", ".");
    }

    public String getSuperClassName() {
        ConstantInfo constantInfo = constantPool.getConstantInfos()[superClass];
        String name = (String) constantInfo.getValue();
        return name.replace("/", ".");
    }

    public void initClassFileInfo(InputStream classFileStream) {
        try {

            DataInputStream dataInputStream = new DataInputStream(classFileStream);

            this.setMagic(dataInputStream.readInt());

            this.setMinorVersion(dataInputStream.readShort());
            this.setMajorVersion(dataInputStream.readShort());

            this.setConstantPoolCount(dataInputStream.readShort());
            this.setConstantPool(initConstantPoolInfo(dataInputStream, this.getConstantPoolCount()));

            this.setClassAccess(new ClassAccess(dataInputStream.readShort()));

            this.setThisClass(dataInputStream.readShort());
            this.setSuperClass(dataInputStream.readShort());

            this.setInterfaceCount(dataInputStream.readShort());
            this.setInterfaceInfo(initInterfaceInfo(dataInputStream, this.getInterfaceCount()));

            this.setFieldCount(dataInputStream.readShort());
            this.setFieldInfos(initFieldInfo(dataInputStream, this.getConstantPool(), this.getFieldCount()));

            this.setMethodCount(dataInputStream.readShort());
            this.setMethodInfos(initMethodInfo(dataInputStream, this.getConstantPool(), this.getMethodCount()));

            this.setAttributeCount(dataInputStream.readShort());
            this.setAttributeInfo(initAttributeInfo(dataInputStream, this.getConstantPool(), this.getAttributeCount()));
        } catch (Exception e) {
            throw new RuntimeException("resolve class file stream failed", e);
        } finally {
            try {
                classFileStream.close();
            } catch (IOException e) {
            }
        }
    }

    private ConstantPool initConstantPoolInfo(DataInputStream dataInputStream, short count) throws Exception {
        ConstantPool constantPool = new ConstantPool(count);
        for (int i = 1; i < constantPool.getConstantInfos().length; i++) {
            byte tag = dataInputStream.readByte();
            ConstantInfo constantInfo = ConstantInfo.getConstantInfo(tag);
            constantInfo.initConstantInfo(dataInputStream);
            constantPool.getConstantInfos()[i] = constantInfo;
            constantInfo.setConstantPool(constantPool);
            if (constantInfo.getClass() == ConstantLongInfo.class || constantInfo.getClass() == ConstantDoubleInfo.class) {
                i++;
                constantPool.getConstantInfos()[i] = new ConstantPaddingInfo((byte) 0);
                constantPool.getConstantInfos()[i].setConstantPool(constantPool);
            }
        }
        return constantPool;
    }

    private InterfaceInfo initInterfaceInfo(DataInputStream dataInputStream, short count) throws Exception {
        InterfaceInfo interfaceInfo = new InterfaceInfo(count);
        for (int i = 0; i < interfaceInfo.getInterfaceIndexes().length; i++) {
            interfaceInfo.getInterfaceIndexes()[i] = dataInputStream.readShort();
        }
        interfaceInfo.setConstantPool(constantPool);
        return interfaceInfo;
    }

    private FieldInfo[] initFieldInfo(DataInputStream dataInputStream, ConstantPool constantPool, short count) throws Exception {
        FieldInfo[] fields = new FieldInfo[count];
        for (int i = 0; i < fields.length; i++) {
            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.initFieldInfo(dataInputStream, constantPool);
            fieldInfo.setConstantPool(constantPool);
            fields[i] = fieldInfo;
        }
        return fields;
    }

    private MethodInfo[] initMethodInfo(DataInputStream dataInputStream, ConstantPool constantPool, short count) throws Exception {
        MethodInfo[] methodInfos = new MethodInfo[count];
        for (int i = 0; i < methodInfos.length; i++) {
            MethodInfo methodInfo = new MethodInfo();
            methodInfo.initMethodInfo(dataInputStream, constantPool);
            methodInfo.setConstantPool(constantPool);
            methodInfos[i] = methodInfo;

        }
        return methodInfos;
    }

    private AttributeInfo initAttributeInfo(DataInputStream dataInputStream, ConstantPool constantPool, short attributeCount) throws Exception {
        AttributeInfo attributeInfo = new AttributeInfo(attributeCount);
        attributeInfo.initAttributeInfo(dataInputStream, constantPool);
        return attributeInfo;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public short getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(short minorVersion) {
        this.minorVersion = minorVersion;
    }

    public short getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(short majorVersion) {
        this.majorVersion = majorVersion;
    }

    public short getConstantPoolCount() {
        return constantPoolCount;
    }

    public void setConstantPoolCount(short constantPoolCount) {
        this.constantPoolCount = constantPoolCount;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public ClassAccess getClassAccess() {
        return classAccess;
    }

    public void setClassAccess(ClassAccess classAccess) {
        this.classAccess = classAccess;
    }

    public short getThisClass() {
        return thisClass;
    }

    public void setThisClass(short thisClass) {
        this.thisClass = thisClass;
    }

    public short getSuperClass() {
        return superClass;
    }

    public void setSuperClass(short superClass) {
        this.superClass = superClass;
    }

    public short getInterfaceCount() {
        return interfaceCount;
    }

    public void setInterfaceCount(short interfaceCount) {
        this.interfaceCount = interfaceCount;
    }

    public InterfaceInfo getInterfaceInfo() {
        return interfaceInfo;
    }

    public void setInterfaceInfo(InterfaceInfo interfaceInfo) {
        this.interfaceInfo = interfaceInfo;
    }

    public short getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(short fieldCount) {
        this.fieldCount = fieldCount;
    }

    public FieldInfo[] getFieldInfos() {
        return fieldInfos;
    }

    public void setFieldInfos(FieldInfo[] fieldInfos) {
        this.fieldInfos = fieldInfos;
    }

    public short getMethodCount() {
        return methodCount;
    }

    public void setMethodCount(short methodCount) {
        this.methodCount = methodCount;
    }

    public MethodInfo[] getMethodInfos() {
        return methodInfos;
    }

    public void setMethodInfos(MethodInfo[] methodInfos) {
        this.methodInfos = methodInfos;
    }

    public short getAttributeCount() {
        return attributeCount;
    }

    public void setAttributeCount(short attributeCount) {
        this.attributeCount = attributeCount;
    }

    public AttributeInfo getAttributeInfo() {
        return attributeInfo;
    }

    public void setAttributeInfo(AttributeInfo attributeInfo) {
        this.attributeInfo = attributeInfo;
    }
}
