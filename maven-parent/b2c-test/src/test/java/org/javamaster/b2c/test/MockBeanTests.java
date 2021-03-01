package org.javamaster.b2c.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.javamaster.b2c.test.config.MyTestsConfiguration;
import org.javamaster.b2c.test.service.RemoteService;
import org.javamaster.b2c.test.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * MockBean会代理bean的所有方法,对于未指定返回值的方法调用均是返回null
 *
 * @author yudong
 * @date 2021/2/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"env=test"})
@Import(MyTestsConfiguration.class)
public class MockBeanTests {

    @MockBean
    private RemoteService remoteService;
    @Autowired
    private UserService userService;

    @Test
    public void selectActorsTest() {
        given(remoteService.getUserMobileById(1)).willReturn("13800138000");
        given(remoteService.getUserMobileById(2)).willReturn("13800138001");

        List<Map<String, String>> mapList = userService.selectActors(Arrays.asList(1, 2));
        assertThat(mapList.get(0).get("phone"), equalTo("13800138001"));
    }

}
