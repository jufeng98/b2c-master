package org.javamaster.b2c.test;

import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yudong
 * @date 2021/2/24
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"env=test"})
public class TestRestTemplateTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getNameTest() {
        String body = restTemplate.getForObject("/user/getName?id=42", String.class);
        log.info("body is:{}", body);
        assertThat(body).isEqualTo("[{\"name\":\"jufeng98\"}]");
    }

}
