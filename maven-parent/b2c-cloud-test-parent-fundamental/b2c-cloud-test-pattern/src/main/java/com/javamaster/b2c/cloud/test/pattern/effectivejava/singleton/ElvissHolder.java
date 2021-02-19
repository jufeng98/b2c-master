package com.javamaster.b2c.cloud.test.pattern.effectivejava.singleton;

/**
 * Created on 2018/8/15.<br/>
 *
 * @author yudong
 */
public class ElvissHolder {

    public static Elviss getInstanceGood() {
        return Elviss.elviss;
    }

    private static class Elviss {
        public static final Elviss elviss = new Elviss();
    }

}
