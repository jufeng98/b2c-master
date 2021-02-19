package org.javamaster.b2c.actuator.config;

import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author yudong
 * @date 2020/7/30
 */
@Configuration
public class ActuatorConfig {
    /**
     * 该bean可以将/trace容量调整至1000个条目
     */
    @Bean
    @Primary
    public InMemoryHttpTraceRepository traceRepository() {
        InMemoryHttpTraceRepository traceRepo = new InMemoryHttpTraceRepository();
        traceRepo.setCapacity(1000);
        return traceRepo;
    }
}
