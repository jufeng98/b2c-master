package org.summerframework.summer.web.config;


import javax.servlet.ServletRegistration;

/**
 * @author yudong
 */
public interface WebConfig {

    /**
     * 配置DispatchServlet
     *
     * @param dynamic
     */
    default void configDispatchServlet(ServletRegistration.Dynamic dynamic) {
        dynamic.setLoadOnStartup(0);
        dynamic.addMapping("/front/*");
    }

}
