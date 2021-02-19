package org.javamaster.b2c.https;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yudong
 */
@SpringBootApplication
public class HttpsApplication {
    static Logger logger = LoggerFactory.getLogger(HttpsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HttpsApplication.class, args);
        logger.info("http://localhost:9700/welcome");
        logger.info("https://localhost:9443/welcome");
    }

}
