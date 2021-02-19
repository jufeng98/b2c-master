package org.javamaster.b2c.rabbitmq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yudong
 */
@SpringBootApplication
public class RabbitMqApplication {

    private static Logger logger = LoggerFactory.getLogger(RabbitMqApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApplication.class, args);
        logger.info("manage url:{}", "http://127.0.0.1:15672/");
        logger.info("name:{},pwd:{}", "root", "root");
    }

}
