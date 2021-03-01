package org.javamaster.b2c.test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * only WebFlux applications are supported.
 *
 * @author yudong
 * @date 2021/2/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class, properties = {"env=test"})
@AutoConfigureWebTestClient
public class WebTestClientTests {
    @Autowired
    private WebTestClient webClient;

    @Test
    @Ignore
    public void getNameTest() {
        webClient.get()
                .uri("/user/getName?id=42")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("[{\"name\":\"jufeng98\"}]");
    }

}
