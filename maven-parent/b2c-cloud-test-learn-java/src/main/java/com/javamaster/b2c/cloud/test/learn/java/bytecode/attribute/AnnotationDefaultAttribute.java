package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;

import java.io.DataInputStream;

/**
 * 描述注解的默认值(1.5)
 *
 * @author yudong
 * @date 2019/6/26
 */
public class AnnotationDefaultAttribute extends Attribute {

    private AnnotationsAttribute.AnnotationIndexTable.AnnotationAttribute.AnnotationAttributeValue annotationAttributeValue;

    public AnnotationDefaultAttribute(short nameIndex) {
        super(nameIndex);
    }

    @Override
    public void initSubInfo(DataInputStream infoStream) throws Exception {
        annotationAttributeValue = AnnotationsAttribute.AnnotationIndexTable.AnnotationAttribute.AnnotationAttributeValue.read(infoStream);
    }

    public AnnotationsAttribute.AnnotationIndexTable.AnnotationAttribute.AnnotationAttributeValue getAnnotationAttributeValue() {
        return annotationAttributeValue;
    }

    public void setAnnotationAttributeValue(AnnotationsAttribute.AnnotationIndexTable.AnnotationAttribute.AnnotationAttributeValue annotationAttributeValue) {
        this.annotationAttributeValue = annotationAttributeValue;
    }
}
