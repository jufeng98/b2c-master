package org.javamaster.b2c.test;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.test.listener.CustomTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration
@TestExecutionListeners(value = {CustomTestExecutionListener.class}, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public class TestExecutionListenerTests {

    @Test
    public void exampleTest() {
    }

}
