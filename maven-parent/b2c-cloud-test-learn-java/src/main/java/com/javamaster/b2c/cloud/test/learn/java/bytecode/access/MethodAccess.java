package com.javamaster.b2c.cloud.test.learn.java.bytecode.access;


import com.javamaster.b2c.cloud.test.learn.java.bytecode.modifier.MethodModifie;

import java.util.List;

/**
 * Created on 2019/1/11.<br/>
 *
 * @author yudong
 */
public class MethodAccess {
    private final short accessFlags;
    private final List<MethodModifie> modifiers;


    public MethodAccess(short accessFlags) {
        this.accessFlags = accessFlags;
        modifiers = MethodModifie.getModifiers(accessFlags);
    }

    public short getAccessFlags() {
        return accessFlags;
    }

    public List<MethodModifie> getModifiers() {
        return modifiers;
    }
}
