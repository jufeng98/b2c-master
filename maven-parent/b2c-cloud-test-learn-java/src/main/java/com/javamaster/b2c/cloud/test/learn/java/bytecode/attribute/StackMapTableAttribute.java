package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;

import com.google.common.collect.Lists;
import static java.util.stream.Collectors.toList;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 描述类型校验时需要用到的相关信息,加快class文件的校验速度(1.6)
 *
 * @author yudong
 * @date 2019/6/26
 */
public class StackMapTableAttribute extends Attribute {
    private short numberOfEntrys;
    private List<List<Byte>> stackMapFrames = Lists.newArrayList();

    public StackMapTableAttribute(short nameIndex) {
        super(nameIndex);
    }

    @Override
    public void initSubInfo(DataInputStream infoStream) throws Exception {
        numberOfEntrys = infoStream.readShort();
        stackMapFrames = IntStream.range(0, numberOfEntrys)
                .mapToObj(i -> {
                    try {
                        List<Byte> bytes = Lists.newArrayList();
                        for (int j = 0; j < 4; j++) {
                            bytes.add(infoStream.readByte());
                        }
                        return bytes;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(toList());
    }

    public short getNumberOfEntrys() {
        return numberOfEntrys;
    }

    public void setNumberOfEntrys(short numberOfEntrys) {
        this.numberOfEntrys = numberOfEntrys;
    }

    public List<List<Byte>> getStackMapFrames() {
        return stackMapFrames;
    }

    public void setStackMapFrames(List<List<Byte>> stackMapFrames) {
        this.stackMapFrames = stackMapFrames;
    }
}
