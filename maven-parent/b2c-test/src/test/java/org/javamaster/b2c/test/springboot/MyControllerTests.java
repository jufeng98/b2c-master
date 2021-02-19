package org.javamaster.b2c.test.springboot;

import org.javamaster.b2c.test.controller.UserController;
import org.javamaster.b2c.test.service.RemoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class MyControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RemoteService remoteService;

    @Test
    public void testExample() throws Exception {
        given(remoteService.getUserNameByMobile("18826483963")).willReturn("liangyudong");

        this.mvc.perform(get("/user/getName?mobile=18826483963").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("liangyudong"));
    }

}
