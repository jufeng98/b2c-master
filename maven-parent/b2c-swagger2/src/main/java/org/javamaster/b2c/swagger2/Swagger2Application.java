package org.javamaster.b2c.swagger2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yudong
 * @date 2019/12/19
 */
@Slf4j
@EnableSwagger2
@SpringBootApplication
public class Swagger2Application {

    private static ApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(Swagger2Application.class, args);
        Environment environment = context.getEnvironment();
        log.info("swagger2 url:http://localhost:{}/swagger-ui.html#/", environment.getProperty("server.port"));
        log.info("swagger2 url:http://localhost:{}/doc.html#/", environment.getProperty("server.port"));
    }

    public static ApplicationContext getContext() {
        return context;
    }

}
