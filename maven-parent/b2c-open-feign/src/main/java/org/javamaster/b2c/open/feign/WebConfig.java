package org.javamaster.b2c.open.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yudong
 * @date 2022/4/3
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    @Primary
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        return new CustomJsonMapper();
    }


}
