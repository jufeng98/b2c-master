package com.javamaster.b2c.cloud.test.learn.java.bytecode.access;


import com.javamaster.b2c.cloud.test.learn.java.bytecode.modifier.ClassModifier;

import java.util.List;


/**
 * 访问标志，access_flags 是一种掩码标志，用于表示某个类或者接口的访问权限及基
 * 础属性
 *
 * @author yudong
 * @date 2019/1/11
 */
public class ClassAccess {
    private final short accessFlags;
    private final List<ClassModifier> modifiers;

    public ClassAccess(short accessFlags) {
        this.accessFlags = accessFlags;
        modifiers = ClassModifier.getModifiers(accessFlags);
    }

    public short getAccessFlags() {
        return accessFlags;
    }

    public List<ClassModifier> getModifiers() {
        return modifiers;
    }


}
