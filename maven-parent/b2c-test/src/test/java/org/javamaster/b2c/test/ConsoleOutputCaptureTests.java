package org.javamaster.b2c.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import org.javamaster.b2c.test.config.MyTestsConfiguration;
import org.javamaster.b2c.test.service.RemoteService;
import org.javamaster.b2c.test.service.UserService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

/**
 * @author yudong
 * @date 2021/2/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"env=test"})
@Import(MyTestsConfiguration.class)
public class ConsoleOutputCaptureTests {

    @MockBean
    private RemoteService remoteService;
    @Autowired
    private UserService userService;
    @Rule
    public OutputCapture capture = new OutputCapture();

    @Test
    public void selectActorsTest() {
        given(remoteService.getUserMobileById(1)).willReturn("13800138000");

        userService.selectActors(Collections.singletonList(1));
        assertThat(capture.toString(), containsString("actorId:1,phone:13800138000"));

    }

}
