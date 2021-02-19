package org.javamaster.b2c.test.springboot;

import org.javamaster.b2c.test.config.MyTestsConfiguration;
import org.javamaster.b2c.test.service.RemoteService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(MyTestsConfiguration.class)
public class MyTests {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private RemoteService remoteService;

    @Test
    public void exampleTest() {
        given(remoteService.getUserNameByMobile("18826483963")).willReturn("liangyudong");
        String username = remoteService.getUserNameByMobile("18826483963");
        assertThat(username).isEqualTo("liangyudong");
    }

    @Test
    public void exampleTest1() throws Exception {
        this.mvc.perform(get("/actuator/info"))
                .andExpect(status().isOk())
                .andExpect(content().string("{}"));
    }

    @Rule
    public OutputCapture capture = new OutputCapture();

    @Test
    public void testName() {
        System.out.println("Hello World!");
//        assertThat(capture.toString(), containsString("World"));
    }
}
