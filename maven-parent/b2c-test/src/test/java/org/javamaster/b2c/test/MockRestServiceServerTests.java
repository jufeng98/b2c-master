package org.javamaster.b2c.test;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.test.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import org.springframework.web.client.RestTemplate;


/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"env=test"})
public class MockRestServiceServerTests {
    @MockBean
    private RemoteService remoteService;
    @MockBean
    private TestService testService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;

    @Test
    @SneakyThrows
    public void test() {
        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(restTemplate).build();
        mockServer
                .expect(requestTo("https://www.baidu.com/translate?word=hello"))
                .andRespond(withSuccess("world", MediaType.TEXT_PLAIN));

        log.info("res:{}", userService.translate("hello"));

        mockServer.verify();
    }

}
