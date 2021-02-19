package org.javamaster.b2c.test.spring;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.test.bootstrap.MyContextBootstrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@BootstrapWith(MyContextBootstrapper.class)
public class BootstrapWithTests {

    @Test
    public void exampleTest() {
    }

}
