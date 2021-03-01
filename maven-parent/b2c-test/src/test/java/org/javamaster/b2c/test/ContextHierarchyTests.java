package org.javamaster.b2c.test;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.test.config.ChildConfiguration;
import org.javamaster.b2c.test.config.ParentConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextHierarchy({
        @ContextConfiguration(classes = ParentConfiguration.class),
        @ContextConfiguration(classes = ChildConfiguration.class),
})
public class ContextHierarchyTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @SneakyThrows
    public void test() {
        log.info("id:{}", applicationContext.getId());
        log.info("id:{}", Objects.requireNonNull(applicationContext.getParent()).getId());

        log.info("beans:{}", Arrays.toString(applicationContext.getBeanDefinitionNames()));
        log.info("beans:{}", Arrays.toString(applicationContext.getParent().getBeanDefinitionNames()));
    }

}
