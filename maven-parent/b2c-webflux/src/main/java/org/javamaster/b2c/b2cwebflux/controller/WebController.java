package org.javamaster.b2c.b2cwebflux.controller;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author yudong
 * @date 2019/12/9
 */
@RestController
@RequestMapping("/web")
public class WebController {

    @RequestMapping("/sayHello")
    public Mono<String> sayHello(ServerHttpRequest request, WebSession webSession) {
        System.out.println(request.getPath());
        request.getBody().subscribe(dataBuffer -> System.out.println(dataBuffer.toString(StandardCharsets.UTF_8)));
        System.out.println(webSession.getCreationTime());
        return Mono.just("hell web flux");
    }

}
