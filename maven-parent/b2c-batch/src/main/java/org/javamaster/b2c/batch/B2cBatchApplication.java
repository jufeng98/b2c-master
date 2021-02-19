package org.javamaster.b2c.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yudong
 * @date 2020/7/31
 */
@EnableBatchProcessing
@SpringBootApplication
public class B2cBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(B2cBatchApplication.class, args);
    }

}
