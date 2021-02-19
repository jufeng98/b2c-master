package org.summerframework.summer.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author yudong
 * @date 2019/5/14
 */
public interface SummerApplicationInitializer {
    void onStartup(ServletContext servletContext) throws ServletException;
}
