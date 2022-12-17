package com.javamaster.b2c.cloud.test.learn.java.model;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author yudong
 * @date 2022/11/15
 */
public class Reflection {
    protected List<String> stringList = Lists.newArrayList();

    public List<String> getStringList() {
        return this.stringList;
    }

    public void setStringList(List<String> list) {
        this.stringList = list;
    }
}
