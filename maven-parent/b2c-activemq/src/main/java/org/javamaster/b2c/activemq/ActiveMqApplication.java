package org.javamaster.b2c.activemq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yudong
 */
@SpringBootApplication
public class ActiveMqApplication {

    private static Logger logger = LoggerFactory.getLogger(ActiveMqApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ActiveMqApplication.class, args);
        logger.info("manage url:{}", "http://127.0.0.1:8161/");
        logger.info("name:{},pwd:{}", "admin", "admin");
    }

}
