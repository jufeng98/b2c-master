package com.javamaster.b2c.cloud.test.pattern.effectivejava.immutable;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.Date;

/**
 * 保护性拷贝,构建真正不可变对象<br/>
 * Created on 2018/8/8.<br/>
 *
 * @author yudong
 */
@Immutable
public class Period {

    private final Date start;
    private final Date end;

    public Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        if (start.before(end)) {
            throw new IllegalArgumentException(start + " after " + end);
        }
    }

    public Date getStart() {
        return new Date(start.getTime());
    }

    public Date getEnd() {
        return new Date(end.getTime());
    }
}
