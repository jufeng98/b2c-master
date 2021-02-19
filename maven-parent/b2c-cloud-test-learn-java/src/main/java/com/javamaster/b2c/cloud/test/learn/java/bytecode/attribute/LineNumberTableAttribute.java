package com.javamaster.b2c.cloud.test.learn.java.bytecode.attribute;

import com.google.common.collect.Lists;

import java.io.DataInputStream;
import java.util.List;

/**
 * 描述源码行号和字节码行号的对应关系
 *
 * @author yudong
 * @date 2019/6/26
 */
public class LineNumberTableAttribute extends Attribute {
    private short lineNumberTableLength;
    private List<LineNumberTable> lineNumberTable = Lists.newArrayList();

    public LineNumberTableAttribute(short nameIndex) {
        super(nameIndex);
    }

    @Override
    public void initSubInfo(DataInputStream infoStream) throws Exception {
        lineNumberTableLength = infoStream.readShort();
        for (int i = 0; i < lineNumberTableLength; i++) {
            lineNumberTable.add(new LineNumberTable(infoStream.readShort(), infoStream.readShort()));
        }
    }

    public short getLineNumberTableLength() {
        return lineNumberTableLength;
    }

    public void setLineNumberTableLength(short lineNumberTableLength) {
        this.lineNumberTableLength = lineNumberTableLength;
    }

    public List<LineNumberTable> getLineNumberTable() {
        return lineNumberTable;
    }

    public void setLineNumberTable(List<LineNumberTable> lineNumberTable) {
        this.lineNumberTable = lineNumberTable;
    }

    public static class LineNumberTable {
        /**
         * 字节码行号
         */
        private short startPc;
        /**
         * 源码行号
         */
        private short lineNumber;

        public LineNumberTable(short startPc, short lineNumber) {
            this.startPc = startPc;
            this.lineNumber = lineNumber;
        }

        public short getStartPc() {
            return startPc;
        }

        public void setStartPc(short startPc) {
            this.startPc = startPc;
        }

        public short getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(short lineNumber) {
            this.lineNumber = lineNumber;
        }
    }
}
