package com.javamaster.b2c.cloud.test.summer.config;

import org.summerframework.summer.web.anno.SummerConfiguration;
import org.summerframework.summer.web.config.WebConfig;

import javax.servlet.ServletRegistration;

/**
 * @author yudong
 */
@SummerConfiguration
public class WebConfiguration implements WebConfig {
    @Override
    public void configDispatchServlet(ServletRegistration.Dynamic dynamic) {
        dynamic.setLoadOnStartup(0);
        dynamic.addMapping("/api/*");
    }
}
