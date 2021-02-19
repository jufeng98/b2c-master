package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;

import com.javamaster.b2c.cloud.test.learn.java.bytecode.tablestructure.ConstantInfo;
import com.google.common.collect.Lists;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yudong
 * @date 2019/6/26
 */
public class ParameterAnnotationsAttribute extends Attribute {
    private byte numberOfParameters;
    private List<ParameterAnnotationTable> parameterAnnotationTable = Lists.newArrayList();

    public ParameterAnnotationsAttribute(short nameIndex) {
        super(nameIndex);
    }

    @Override
    public void initSubInfo(DataInputStream infoStream) throws Exception {
        numberOfParameters = infoStream.readByte();
        parameterAnnotationTable = IntStream.range(0, numberOfParameters)
                .mapToObj(i -> {
                    ParameterAnnotationTable parameterAnnotationTable = new ParameterAnnotationTable();
                    try {
                        parameterAnnotationTable.initAttribute(infoStream);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return parameterAnnotationTable;

                })
                .collect(Collectors.toList());
    }

    public byte getNumberOfParameters() {
        return numberOfParameters;
    }

    public void setNumberOfParameters(byte numberOfParameters) {
        this.numberOfParameters = numberOfParameters;
    }

    public List<ParameterAnnotationTable> getParameterAnnotationTable() {
        return parameterAnnotationTable;
    }

    public void setParameterAnnotationTable(List<ParameterAnnotationTable> parameterAnnotationTable) {
        this.parameterAnnotationTable = parameterAnnotationTable;
    }

    public class ParameterAnnotationTable {
        private short numberOfAnnotations;
        private List<Short> annotations;

        public void initAttribute(DataInputStream stream) throws Exception {
            numberOfAnnotations = stream.readShort();
            if (numberOfAnnotations == 0) {
                return;
            }
            annotations = IntStream.range(0, numberOfAnnotations)
                    .mapToObj(i -> {
                        try {
                            return stream.readShort();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
        }

        public List<Object> getAnnotationValues() {
            return annotations.stream()
                    .map(aShort -> {
                        ConstantInfo constantInfo = constantPool.getConstantInfos()[aShort];
                        return constantInfo.getValue();
                    })
                    .collect(Collectors.toList());
        }

        public short getNumberOfAnnotations() {
            return numberOfAnnotations;
        }

        public void setNumberOfAnnotations(short numberOfAnnotations) {
            this.numberOfAnnotations = numberOfAnnotations;
        }

        public List<Short> getAnnotations() {
            return annotations;
        }

        public void setAnnotations(List<Short> annotations) {
            this.annotations = annotations;
        }
    }
}
