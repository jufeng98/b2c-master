package com.javamaster.b2c.cloud.test.learn.java.dynamiccompiler;

import javax.tools.SimpleJavaFileObject;

/**
 * Created on 2019/1/25.<br/>
 *
 * @author yudong
 */
public class StringBuilderJavaSource extends SimpleJavaFileObject {

    private StringBuilder code;

    public StringBuilderJavaSource(String name, StringBuilder code) {
        super(java.net.URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension),
                Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }
}
