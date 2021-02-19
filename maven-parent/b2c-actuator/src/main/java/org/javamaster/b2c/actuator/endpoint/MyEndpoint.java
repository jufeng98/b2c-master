package org.javamaster.b2c.actuator.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yudong
 * @date 2020/7/30
 */
@Component
@Endpoint(id = "my-endpoint")
public class MyEndpoint {

    @ReadOperation(produces = "application/json")
    public Map<String, Object> read() {
        Map<String, Object> map = new HashMap<>(1, 1);
        map.put("welcome", "welcome to actuator world!");
        return map;
    }
}
