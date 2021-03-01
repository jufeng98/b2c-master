package org.javamaster.b2c.test.config;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.test.service.RemoteService;
import org.springframework.context.annotation.*;

/**
 * @author yudong
 * @date 2021/2/26
 */
@Slf4j
@Configuration
public class WeConfig {

    @Bean
    @Profile("dev")
    public RemoteService remoteService() {
        return id -> {
            if (id == 42) {
                return "13800138000";
            } else {
                return null;
            }
        };
    }


}
