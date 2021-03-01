package org.javamaster.b2c.test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.spel.standard.SpelExpressionParser;

@Slf4j
@TestConfiguration
public class ChildConfiguration {

    @Bean
    public SpelExpressionParser spelExpressionParserChild() {
        log.info("init child spelExpressionParser");
        return new SpelExpressionParser();
    }
}
