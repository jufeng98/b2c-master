package org.javamaster.b2c.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DefaultTestContextBootstrapper;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@BootstrapWith(BootstrapWithTests.ContextBootstrapper.class)
public class BootstrapWithTests {

    @Test
    public void test() {
        System.out.println();
    }

    /**
     * @see SpringBootTestContextBootstrapper
     */
    @Slf4j
    @SuppressWarnings("ALL")
    public static class ContextBootstrapper extends DefaultTestContextBootstrapper {

        @Override
        public void setBootstrapContext(BootstrapContext bootstrapContext) {
            super.setBootstrapContext(bootstrapContext);
            CacheAwareContextLoaderDelegate cacheAwareContextLoaderDelegate = bootstrapContext.getCacheAwareContextLoaderDelegate();
            Class<?> testClass = bootstrapContext.getTestClass();
            BootstrapWithTests.log.info(testClass.getName());
        }

        @Override
        public BootstrapContext getBootstrapContext() {
            return super.getBootstrapContext();
        }

        @Override
        public TestContext buildTestContext() {
            TestContext testContext = super.buildTestContext();
            log.info(testContext.getApplicationContext().getId());
            return testContext;
        }

    }
}
