package org.javamaster.b2c.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author yudong
 * @date 2019/12/19
 */
@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class AdminApplication {

    static Logger logger = LoggerFactory.getLogger(AdminApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AdminApplication.class, args);
        Environment env = context.getEnvironment();
        logger.info("http://localhost:{}/wallboard", env.getProperty("server.port"));
    }

}
