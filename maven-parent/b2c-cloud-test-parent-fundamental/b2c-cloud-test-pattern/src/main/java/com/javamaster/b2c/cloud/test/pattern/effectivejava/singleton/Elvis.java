package com.javamaster.b2c.cloud.test.pattern.effectivejava.singleton;

import java.io.Serializable;

/**
 * Created on 2018/8/3.</br>
 *
 * @author yudong
 */
public class Elvis implements Serializable {
    private static Elvis elvis = new Elvis();

    private Elvis() {

    }

    public static Elvis getInstance() {
        return elvis;
    }

    private Object readResolve() {
        return elvis;
    }
}
