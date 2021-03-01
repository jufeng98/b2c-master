package org.javamaster.b2c.test.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
public class CustomTestExecutionListener implements TestExecutionListener {
    @Override
    public void beforeTestMethod(TestContext testContext) {
        log.info("beforeTestMethod:{}", testContext.getTestMethod().toString());
    }

    @Override
    public void afterTestMethod(TestContext testContext) {
        log.info("afterTestMethod:{}", testContext.getTestMethod().toString());
    }
}
