package org.javamaster.b2c.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import static org.javamaster.b2c.gateway.filter.MhGatewayFilter.resolveBodyFromRequest;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author yudong
 * @date 2021/8/25
 */
@Slf4j
@Component
public class BaseGatewayFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("{}", exchange.getRequest().getURI());
        log.info("{}", resolveBodyFromRequest(exchange.getRequest()));
        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
