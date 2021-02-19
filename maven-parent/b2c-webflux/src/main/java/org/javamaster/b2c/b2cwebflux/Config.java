package org.javamaster.b2c.b2cwebflux;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @author yudong
 * @date 2019/12/9
 */
@Configuration
public class Config implements WebFluxConfigurer {
    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        Jackson2JsonDecoder decoder = new Jackson2JsonDecoder();
        configObjectMapper(decoder.getObjectMapper());
        configurer.defaultCodecs().jackson2JsonDecoder(decoder);

        Jackson2JsonEncoder encoder = new Jackson2JsonEncoder();
        configObjectMapper(encoder.getObjectMapper());
        encoder.getObjectMapper();
        configurer.defaultCodecs().serverSentEventEncoder(encoder);
    }

    private void configObjectMapper(ObjectMapper objectMapper) {
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

}
