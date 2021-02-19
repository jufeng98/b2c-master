package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author yudong
 * @date 2019/6/26
 */
public enum AttributeTypeEnum {
    CODE("Code", CodeAttribute.class),
    EXCEPTIONS("Exceptions", ExceptionsAttribute.class),
    LINE_NUMBER_TABLE("LineNumberTable", LineNumberTableAttribute.class),
    LOCAL_VARIABLE_TABLE("LocalVariableTable", LocalVariableTableAttribute.class),
    LOCAL_VARIABLE_TYPE_TABLE("LocalVariableTypeTable", LocalVariableTypeTableAttribute.class),
    SOURCE_FILE("SourceFile", SourceFileAttribute.class),
    CONSTANT_VALUE("ConstantValue", ConstantValueAttribute.class),
    INNER_CLASSES("InnerClasses", InnerClassesAttribute.class),
    DEPRECATED("Deprecated", DeprecatedAttribute.class),
    SYNTHETIC("Synthetic", SyntheticAttribute.class),
    STACK_MAP_TABLE("StackMapTable", StackMapTableAttribute.class),
    ENCLOSING_METHOD("EnclosingMethod", EnclosingMethodAttribute.class),
    SIGNATURE("Signature", SignatureAttribute.class),
    SOURCE_DEBUG_EXTENSION("SourceDebugExtension", SourceDebugExtensionAttribute.class),
    RUNTIME_VISIBLE_ANNOTATIONS("RuntimeVisibleAnnotations", RuntimeVisibleAnnotationsAttribute.class),
    RUNTIME_INVISIBLE_ANNOTATIONS("RuntimeInvisibleAnnotations", RuntimeInvisibleAnnotationsAttribute.class),
    RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS("RuntimeVisibleParameterAnnotations", RuntimeVisibleParameterAnnotationsAttribute.class),
    RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS("RuntimeInvisibleParameterAnnotations", RuntimeInvisibleParameterAnnotationsAttribute.class),
    ANNOTATION_DEFAULT("AnnotationDefault", AnnotationDefaultAttribute.class),
    ;
    private String attributeName;
    private Class<? extends Attribute> attributeTypeClz;
    private static Map<String, Class<? extends Attribute>> map = Maps.newHashMap();

    static {
        for (AttributeTypeEnum value : AttributeTypeEnum.values()) {
            map.put(value.attributeName, value.attributeTypeClz);
        }
    }

    AttributeTypeEnum(String attributeName, Class<? extends Attribute> attributeTypeClz) {
        this.attributeName = attributeName;
        this.attributeTypeClz = attributeTypeClz;
    }

    public static Class<? extends Attribute> getAttributeType(String attributeName) {
        return map.getOrDefault(attributeName, Attribute.class);
    }
}
