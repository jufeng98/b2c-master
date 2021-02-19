package org.javamaster.b2c.b2cwebflux;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

class WebfluxApplicationTests {

    private RestTemplate restTemplate;

    @BeforeEach
    void init() {
        restTemplate = new RestTemplate();
    }

    @Test
    void contextLoads() {
        String res = restTemplate.postForObject("http://localhost:8812/web/sayHello", "{\"name\":\"liangyudong\"}", String.class);
        System.out.println(res);
    }

}
