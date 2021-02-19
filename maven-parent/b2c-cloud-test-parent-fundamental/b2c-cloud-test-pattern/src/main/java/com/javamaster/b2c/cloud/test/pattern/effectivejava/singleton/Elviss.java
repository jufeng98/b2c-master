package com.javamaster.b2c.cloud.test.pattern.effectivejava.singleton;

/**
 * Created on 2018/8/15.<br/>
 *
 * @author yudong
 */
public class Elviss {

    private static volatile Elviss elviss;

    public Elviss getInstance() {
        if (elviss == null) {
            synchronized (Elviss.class) {
                if (elviss == null) {
                    elviss = new Elviss();
                }
            }
        }
        return elviss;
    }
}
