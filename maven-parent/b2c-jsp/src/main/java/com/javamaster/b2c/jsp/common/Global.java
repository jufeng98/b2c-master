package com.javamaster.b2c.jsp.common;

import org.springframework.context.ApplicationContext;

/**
 * @author yudong
 * @date 2020/3/18
 */
public class Global {

    public static ApplicationContext context;

    public static String getAdminPath() {
        return context.getEnvironment().getProperty("app.adminPath");
    }

}
