package org.javamaster.b2c.spring.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author yudong
 * @date 2020/6/4
 */
@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringAopApplication {

    public static ExpressionParser parser = new SpelExpressionParser();

    public static void main(String[] args) {
        SpringApplication.run(SpringAopApplication.class, args);
    }

    @Bean
    public ExpressionParser expressionParser() {
        return parser;
    }
}
