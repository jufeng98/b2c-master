package com.javamaster.b2c.cloud.test.summer.helper;

import org.summerframework.summer.core.anno.SummerComponent;

@SummerComponent
public class HelloHelper {

    private static final String PREFIX = "welcome";

    public String getWelcome() {
        return PREFIX;
    }

}
