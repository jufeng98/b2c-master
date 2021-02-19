package org.javamaster.b2c.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

/**
 * <p>actuator的使用</p>
 * <a href="https://docs.spring.io/spring-boot/docs/2.3.2.RELEASE/reference/html/production-ready-features.html#production-ready">官方文档</a>
 * <br/>
 * <a href="https://docs.spring.io/spring-boot/docs/2.3.2.RELEASE/actuator-api/html/">endpoints详细说明官方文档</a>
 *
 * @author yudong
 * @date 2020/7/30
 */
@SpringBootApplication
public class B2cActuatorApplication {

    static Logger logger = LoggerFactory.getLogger(B2cActuatorApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(B2cActuatorApplication.class, args);
        Environment env = context.getEnvironment();
        logger.info("http://localhost:{}/actuator", env.getProperty("server.port"));
    }

}
