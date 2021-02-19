package org.javamaster.b2c.test.bootstrap;

import org.springframework.test.context.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yudong
 * @date 2020/10/23
 */
public class MyContextBootstrapper implements TestContextBootstrapper {

    @Override
    public void setBootstrapContext(BootstrapContext bootstrapContext) {

    }

    @Override
    public BootstrapContext getBootstrapContext() {
        return null;
    }

    @Override
    public TestContext buildTestContext() {
        return null;
    }

    @Override
    public MergedContextConfiguration buildMergedContextConfiguration() {
        return null;
    }

    @Override
    public List<TestExecutionListener> getTestExecutionListeners() {
        return new ArrayList<>();
    }
}
