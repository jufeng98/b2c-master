package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;

import com.javamaster.b2c.cloud.test.learn.java.bytecode.ConstantPool;
import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.Lists;

import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yudong
 * @date 2019/6/26
 */
public class AnnotationsAttribute extends Attribute {
    private short numberOfAnnotations;
    private List<AnnotationIndexTable> annotationIndexTable = Lists.newArrayList();

    public AnnotationsAttribute(short nameIndex) {
        super(nameIndex);
    }

    @Override
    public void initSubInfo(DataInputStream infoStream) throws Exception {
        numberOfAnnotations = infoStream.readShort();
        annotationIndexTable = IntStream.range(0, numberOfAnnotations)
                .mapToObj(i -> {
                    AnnotationIndexTable annotationIndexTable = new AnnotationIndexTable();
                    try {
                        annotationIndexTable.initAttribute(infoStream, constantPool);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return annotationIndexTable;

                })
                .collect(Collectors.toList());
    }

    public short getNumberOfAnnotations() {
        return numberOfAnnotations;
    }

    public void setNumberOfAnnotations(short numberOfAnnotations) {
        this.numberOfAnnotations = numberOfAnnotations;
    }

    public List<RuntimeVisibleAnnotationsAttribute.AnnotationIndexTable> getAnnotationIndexTable() {
        return annotationIndexTable;
    }

    public void setAnnotationIndexTable(List<RuntimeVisibleAnnotationsAttribute.AnnotationIndexTable> annotationIndexTable) {
        this.annotationIndexTable = annotationIndexTable;
    }

    public static class AnnotationIndexTable {
        /**
         * 注解符号引用的索引下标
         */
        private short annotationInfoIndex;
        /**
         * 注解的属性数量
         */
        private short numberOfAnnotationAttributes;
        /**
         * 注解的属性键值对
         */
        private List<AnnotationAttribute> annotationAttributes;

        public void initAttribute(DataInputStream infoStream, ConstantPool constantPool) throws Exception {
            annotationInfoIndex = infoStream.readShort();
            numberOfAnnotationAttributes = infoStream.readShort();
            if (numberOfAnnotationAttributes == 0) {
                return;
            }
            annotationAttributes = IntStream.range(0, numberOfAnnotationAttributes)
                    .mapToObj(i -> {
                        AnnotationAttribute annotationAttribute = new AnnotationAttribute();
                        annotationAttribute.initAttribute(infoStream, constantPool);
                        return annotationAttribute;
                    })
                    .collect(Collectors.toList());
        }

        public short getAnnotationInfoIndex() {
            return annotationInfoIndex;
        }

        public void setAnnotationInfoIndex(short annotationInfoIndex) {
            this.annotationInfoIndex = annotationInfoIndex;
        }

        public short getNumberOfAnnotationAttributes() {
            return numberOfAnnotationAttributes;
        }

        public void setNumberOfAnnotationAttributes(short numberOfAnnotationAttributes) {
            this.numberOfAnnotationAttributes = numberOfAnnotationAttributes;
        }

        public List<AnnotationAttribute> getAnnotationAttributes() {
            return annotationAttributes;
        }

        public void setAnnotationAttributes(List<AnnotationAttribute> annotationAttributes) {
            this.annotationAttributes = annotationAttributes;
        }

        @JsonPropertyOrder({"attributeKeyIndex", "attributeValueIndex", "attributeKey", "attributeValue"})
        public static class AnnotationAttribute {
            /**
             * 注解属性索引下标
             */
            private short attributeKeyIndex;

            private AnnotationAttributeValue annotationAttributeValue;

            @JsonIgnore
            private ConstantPool constantPool;

            public void initAttribute(DataInputStream infoStream, ConstantPool constantPool) {
                try {
                    this.constantPool = constantPool;
                    attributeKeyIndex = infoStream.readShort();
                    annotationAttributeValue = AnnotationAttributeValue.read(infoStream);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            public Object getAttributeKey() {
                ConstantInfo constantInfo = constantPool.getConstantInfos()[attributeKeyIndex];
                return constantInfo.getValue();
            }

            public short getAttributeKeyIndex() {
                return attributeKeyIndex;
            }

            public void setAttributeKeyIndex(short attributeKeyIndex) {
                this.attributeKeyIndex = attributeKeyIndex;
            }

            public AnnotationAttributeValue getAnnotationAttributeValue() {
                return annotationAttributeValue;
            }

            public void setAnnotationAttributeValue(AnnotationAttributeValue annotationAttributeValue) {
                this.annotationAttributeValue = annotationAttributeValue;
            }

            public static class AnnotationAttributeValue {
                protected byte tag;

                public AnnotationAttributeValue(byte tag) {
                    this.tag = tag;
                }

                public byte getTag() {
                    return tag;
                }

                public void setTag(byte tag) {
                    this.tag = tag;
                }

                public static AnnotationAttributeValue read(DataInputStream infoStream) throws Exception {
                    byte var1 = infoStream.readByte();
                    switch (var1) {
                        case 64:
                            AnnotationIndexTable annotationIndexTable = new AnnotationIndexTable();
                            annotationIndexTable.initAttribute(infoStream, null);
                            return new AnnoAnnotationAttributeValue(var1, annotationIndexTable);
                        case 66:
                        case 67:
                        case 68:
                        case 70:
                        case 73:
                        case 74:
                        case 83:
                        case 90:
                        case 115:
                            return new PrimitiveAnnotationAttributeValue(var1, infoStream.readShort());
                        case 91:
                            short s = infoStream.readShort();
                            List<AnnotationAttributeValue> annotationAttributeValues = new ArrayList<>(s);
                            IntStream.range(0, s).forEach(i -> {
                                try {
                                    annotationAttributeValues.add(AnnotationAttributeValue.read(infoStream));
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            });
                            return new ArrayAnnotationAttributeValue(var1, s, annotationAttributeValues);
                        case 99:
                            return new ClassAnnotationAttributeValue(var1, infoStream.readShort());
                        case 101:
                            return new EnumAnnotationAttributeValue(var1, infoStream.readShort(), infoStream.readShort());
                        default:
                            throw new RuntimeException();
                    }

                }

                public static class PrimitiveAnnotationAttributeValue extends AnnotationAttributeValue {
                    private short constValueIndex;

                    public PrimitiveAnnotationAttributeValue(byte tag, short constValueIndex) {
                        super(tag);
                        this.constValueIndex = constValueIndex;
                    }

                    public short getConstValueIndex() {
                        return constValueIndex;
                    }

                    public void setConstValueIndex(short constValueIndex) {
                        this.constValueIndex = constValueIndex;
                    }
                }

                public static class ClassAnnotationAttributeValue extends AnnotationAttributeValue {
                    private short classInfoIndex;

                    public ClassAnnotationAttributeValue(byte tag, short classInfoIndex) {
                        super(tag);
                        this.classInfoIndex = classInfoIndex;
                    }

                    public short getClassInfoIndex() {
                        return classInfoIndex;
                    }

                    public void setClassInfoIndex(short classInfoIndex) {
                        this.classInfoIndex = classInfoIndex;
                    }
                }

                public static class EnumAnnotationAttributeValue extends AnnotationAttributeValue {
                    private short typeNameIndex;
                    private short constNameIndex;

                    public EnumAnnotationAttributeValue(byte tag, short typeNameIndex, short constNameIndex) {
                        super(tag);
                        this.typeNameIndex = typeNameIndex;
                        this.constNameIndex = constNameIndex;
                    }

                    public short getTypeNameIndex() {
                        return typeNameIndex;
                    }

                    public void setTypeNameIndex(short typeNameIndex) {
                        this.typeNameIndex = typeNameIndex;
                    }

                    public short getConstNameIndex() {
                        return constNameIndex;
                    }

                    public void setConstNameIndex(short constNameIndex) {
                        this.constNameIndex = constNameIndex;
                    }
                }

                public static class ArrayAnnotationAttributeValue extends AnnotationAttributeValue {
                    private short numValues;
                    private List<AnnotationAttributeValue> annotationAttributeValues;

                    public ArrayAnnotationAttributeValue(byte tag, short numValues, List<AnnotationAttributeValue> annotationAttributeValues) {
                        super(tag);
                        this.numValues = numValues;
                        this.annotationAttributeValues = annotationAttributeValues;
                    }

                    public short getNumValues() {
                        return numValues;
                    }

                    public void setNumValues(short numValues) {
                        this.numValues = numValues;
                    }

                    public List<AnnotationAttributeValue> getAnnotationAttributeValues() {
                        return annotationAttributeValues;
                    }

                    public void setAnnotationAttributeValues(List<AnnotationAttributeValue> annotationAttributeValues) {
                        this.annotationAttributeValues = annotationAttributeValues;
                    }
                }

                public static class AnnoAnnotationAttributeValue extends AnnotationAttributeValue {
                    private AnnotationIndexTable annotationIndexTable;

                    public AnnoAnnotationAttributeValue(byte tag, AnnotationIndexTable annotationIndexTable) {
                        super(tag);
                        this.annotationIndexTable = annotationIndexTable;
                    }

                    public AnnotationIndexTable getAnnotationIndexTable() {
                        return annotationIndexTable;
                    }

                    public void setAnnotationIndexTable(AnnotationIndexTable annotationIndexTable) {
                        this.annotationIndexTable = annotationIndexTable;
                    }
                }
            }
        }
    }
}
