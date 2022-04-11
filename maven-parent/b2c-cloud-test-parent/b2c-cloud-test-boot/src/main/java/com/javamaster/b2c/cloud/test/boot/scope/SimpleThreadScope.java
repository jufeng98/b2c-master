package com.javamaster.b2c.cloud.test.boot.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * @author yudong
 * @date 2022/4/10
 */
public class SimpleThreadScope implements Scope {
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        return null;
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
