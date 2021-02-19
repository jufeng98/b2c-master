package org.javamaster.b2c.rpc.server;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.RequestContextFilter;

/**
 * Created on 2018/9/7.<br/>
 *
 * @author yudong
 */
@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(RequestContextFilter.class);
        packages("org.javamaster.b2c.rpc.server.controller");
    }
}
