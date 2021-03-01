package org.javamaster.b2c.test;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author yudong
 * @date 2021/2/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class, properties = {"env=test"})
@AutoConfigureMockMvc
public class MockMvcTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void getNameTest() {
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
