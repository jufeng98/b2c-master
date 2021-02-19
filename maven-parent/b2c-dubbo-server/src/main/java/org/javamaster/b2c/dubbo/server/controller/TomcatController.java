package org.javamaster.b2c.dubbo.server.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yudong
 * @date 2020/9/18
 */
@Slf4j
@RestController
@RequestMapping
public class TomcatController {

    private final AtomicInteger atomicInteger = new AtomicInteger();

    @SneakyThrows
    @GetMapping("/test")
    public String test(HttpServletResponse response) {
        log.info("tomcat thread name:{},id:{},count:{}",
                Thread.currentThread().getName(),
                Thread.currentThread().getId(),
                atomicInteger.incrementAndGet());
        response.setStatus(302);
        return "hello";
    }
}
