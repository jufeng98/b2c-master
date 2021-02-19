package org.javamaster.b2c.dubbo.server.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author yudong
 * @date 2020/9/20
 */
@Slf4j
@Component
public class PortRunner implements CommandLineRunner {

    @Autowired
    private ServletWebServerApplicationContext server;

    @Override
    public void run(String... args) {
        log.info("tomcat port is:{}", server.getWebServer().getPort());
    }

}
