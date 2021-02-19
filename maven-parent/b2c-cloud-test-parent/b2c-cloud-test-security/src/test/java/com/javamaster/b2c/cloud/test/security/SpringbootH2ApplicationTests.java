package com.javamaster.b2c.cloud.test.security;

import com.alibaba.fastjson.JSONObject;
import com.javamaster.b2c.cloud.test.security.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringApplication.class)
@WebAppConfiguration
public class SpringbootH2ApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepository repository;

    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "root",
            password = "qq123123",
            roles = "READER")
    public void homePageAuthenticatedUser() {
    }

    @Test
    @WithUserDetails("root")
    public void homePageUser() {

    }

    @Test
    public void test() {
        Object object = jdbcTemplate.queryForMap("select * from person where id = ?", new Object[]{1});
        System.out.println(JSONObject.toJSONString(object));
        Object o = repository.findUserByUserId("218826483963");
        System.out.println(JSONObject.toJSONString(o));
    }
}
