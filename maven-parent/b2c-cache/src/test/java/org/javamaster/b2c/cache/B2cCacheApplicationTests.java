package org.javamaster.b2c.cache;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class B2cCacheApplicationTests {

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mockMvc;

    protected static MockHttpSession session = new MockHttpSession();

    @BeforeAll
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGet() throws Exception {
        String id = "1000001";
        mockMvc.perform(MockMvcRequestBuilders.get("/get")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", id)
                .session(session))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    @Test
    public void testSave() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/save")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("author", "yudong")
                .param("title", "redis master")
                .session(session))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("yudong"));
    }

}
