package org.javamaster.b2c.gateway.factory;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.gateway.filter.BaseGatewayFilter;
import org.javamaster.b2c.gateway.filter.MhGatewayFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author yudong
 * @date 2021/8/25
 */
@Component
@Slf4j
public class AccessGatewayFilterFactory extends AbstractGatewayFilterFactory<AccessGatewayFilterFactory.Config> {
    @Autowired
    private BaseGatewayFilter baseGatewayFilter;
    @Autowired
    private MhGatewayFilter mhGatewayFilter;

    public static final String ACCESS_ORIGIN = "accessOrigin";

    public AccessGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        String mhAccessOrigin = "mhAccessOrigin";
        if (config.getAccessOrigin().equals(mhAccessOrigin)) {
            return mhGatewayFilter;
        } else {
            return baseGatewayFilter;
        }
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(ACCESS_ORIGIN);
    }

    @Data
    public static class Config {
        private String accessOrigin;
    }


}
