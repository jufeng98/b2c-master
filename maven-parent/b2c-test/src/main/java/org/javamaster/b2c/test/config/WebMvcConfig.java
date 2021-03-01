package org.javamaster.b2c.test.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author yudong
 * @date 2021/2/26
 */
@Slf4j
@Configuration
public class WebMvcConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Profile("dev")
    public ObjectMapper objectMapperDev() {
        log.info("create dev bean:{}", ObjectMapper.class.getName());
        return new ObjectMapper();
    }

}
