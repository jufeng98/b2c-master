package org.javamaster.b2c.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yudong
 * @date 2020/5/12
 */
@SpringBootApplication
public class B2cDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(B2cDockerApplication.class, args);
        System.out.println("http://localhost:8080/index");
    }

}
