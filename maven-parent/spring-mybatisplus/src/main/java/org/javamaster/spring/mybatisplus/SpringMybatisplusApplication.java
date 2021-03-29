package org.javamaster.spring.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liangyudong
 * @date 2021/3/19
 */
@MapperScan("org.javamaster.spring.mybatisplus.mapper")
@SpringBootApplication
public class SpringMybatisplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMybatisplusApplication.class, args);
    }

}
