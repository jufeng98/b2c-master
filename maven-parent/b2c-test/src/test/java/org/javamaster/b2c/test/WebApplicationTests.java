package org.javamaster.b2c.test;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.test.config.MyTestsConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
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

    @Test
    public void test() {
        log.info(applicationContext.getId());
        log.info(servletContext.getServerInfo());
    }

}
