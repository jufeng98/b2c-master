package org.javamaster.b2c.test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.client.RestTemplate;

@Slf4j
@TestConfiguration
public class MyTestsConfiguration {

    @Bean
    public SpelExpressionParser spelExpressionParser() {
        log.info("init SpelExpressionParser");
        return new SpelExpressionParser();
    }

    @Bean
    public RestTemplate restTemplate() {
        log.info("init RestTemplate");
        return new RestTemplate();
    }
}
