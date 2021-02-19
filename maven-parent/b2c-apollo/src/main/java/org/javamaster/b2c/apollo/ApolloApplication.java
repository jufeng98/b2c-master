package org.javamaster.b2c.apollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * <p>学习携程apollo配置中心的用法</p>
 * <a href="https://github.com/ctripcorp/apollo/wiki/Quick-Start">官方文档快速开始</a>
 *
 * @author yudong
 * @date 2019/10/29
 */
@EnableApolloConfig
@SpringBootApplication
@ComponentScan("org.javamaster.b2c")
public class ApolloApplication {

    static Logger logger = LoggerFactory.getLogger(ApolloApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ApolloApplication.class, args);
        Environment env = context.getEnvironment();
        logger.info("http://localhost:{}/actuator/info", env.getProperty("server.port"));
    }

}
