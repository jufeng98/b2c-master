package org.javamaster.b2c.test.spring;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.test.config.MyTestsConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

;
;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyTestsConfiguration.class)
@WebAppConfiguration
public class WebApplicationTests {

    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private ServletContext servletContext;

    private MockMvc mockMvc;

    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @Test
    public void exampleTest() {
        log.info(applicationContext.getId());
        log.info(servletContext.getServerInfo());
    }

    @Test
    @SneakyThrows
    public void exampleTest1() {
        this.mockMvc.perform(get("/actuator/info")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @SneakyThrows
    public void exampleTest2() {
        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(restTemplate).build();
        mockServer.expect(requestTo("https://www.baidu.com/greeting")).andRespond(withSuccess("hello", MediaType.TEXT_PLAIN));

        String res = restTemplate.getForObject("https://www.baidu.com/greeting", String.class);
        log.info(res);

        mockServer.verify();
    }

}
