package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;

/**
 * * 作用同RuntimeInvisibleAnnotationsAttribute,不过作用对象为方法参数(1.5)
 *
 * @author yudong
 * @date 2019/6/26
 */
public class RuntimeInvisibleParameterAnnotationsAttribute extends ParameterAnnotationsAttribute {

    public RuntimeInvisibleParameterAnnotationsAttribute(short nameIndex) {
        super(nameIndex);
    }

}
