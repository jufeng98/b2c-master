package com.javamaster.b2c.cloud.test.learn.java.bytecode.access;


import com.javamaster.b2c.cloud.test.learn.java.bytecode.modifier.FieldModifier;

import java.util.List;

/**
 * Created on 2019/1/11.<br/>
 *
 * @author yudong
 */
public class FieldAccess {

    private final short accessFlags;
    private final List<FieldModifier> modifiers;

    public FieldAccess(short accessFlags) {
        this.accessFlags = accessFlags;
        modifiers = FieldModifier.getModifiers(accessFlags);
    }

    public short getAccessFlags() {
        return accessFlags;
    }

    public List<FieldModifier> getModifiers() {
        return modifiers;
    }

}
