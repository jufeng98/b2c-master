package org.javamaster.b2c.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.javamaster.b2c.test.config.MyTestsConfiguration;
import org.javamaster.b2c.test.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * SpyBean可达到部分mock的效果,仅当doReturn("").when(testService).doSomething()时，doSomething方法才被mock，其他的方法仍被真实调用。
 *
 * @author yudong
 * @date 2021/2/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"env=test"})
@Import(MyTestsConfiguration.class)
public class SpyBeanTests {
    @MockBean
    private RemoteService remoteService;
    @SpyBean
    private TestService testService;
    @Autowired
    private UserService userService;

    @Test
    public void selectUserInfoTest() {
        // 未发生实际调用
        doReturn(18).when(testService).getAge(1);
        // 发生了实际调用
        when(testService.getCompany(1)).thenReturn("Apple");

        Map<String, String> map = userService.selectUserInfo(1);
        assertThat(map.get("name"), equalTo("jufeng98"));
        assertThat(map.get("age"), equalTo("18"));
        assertThat(map.get("company"), equalTo("Apple"));
    }

}
