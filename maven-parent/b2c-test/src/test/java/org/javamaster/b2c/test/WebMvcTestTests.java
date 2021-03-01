package org.javamaster.b2c.test;

import lombok.SneakyThrows;
import org.javamaster.b2c.test.controller.UserController;
import org.javamaster.b2c.test.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.HashMap;

/**
 * @author yudong
 * @date 2021/2/24
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class WebMvcTestTests {
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void getNameTest() {
        HashMap<String, String> map = new HashMap<>(1, 1);
        map.put("phone", "13800138000");
        given(userService.selectActors(Collections.singletonList(42))).willReturn(Collections.singletonList(map));

        mockMvc
                .perform(
                        get("/user/getName?id={id}", 42)
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0].phone").value("13800138000"));
    }

}
