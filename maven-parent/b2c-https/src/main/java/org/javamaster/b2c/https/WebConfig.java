package org.javamaster.b2c.https;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author yudong
 * @date 2018/4/13
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Value("${http.port}")
    private int port;

    /**
     * 配置http
     */
    @Bean
    public TomcatEmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setSecure(false);
        connector.setPort(port);
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }


}
