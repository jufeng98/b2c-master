package org.javamaster.b2c.test.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration
// @Sql("/test-schema.sql")
// @SqlMergeMode(MERGE)
// @SqlGroup({
//         @Sql(scripts = "/test-schema.sql", config = @SqlConfig(commentPrefix = "`")),
//         @Sql("/test-user-data.sql")
// })
public class CommitTests {

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeTransaction
    void beforeTransaction() {
        log.info("beforeTransaction");
    }

    @AfterTransaction
    void afterTransaction() {
        log.info("afterTransaction");
    }

    @Test
    @Commit
    @Rollback(false)
    // @Sql({"/test-schema.sql", "/test-user-data.sql"})
    public void exampleTest() {
        log.info(applicationContext.getId());
    }

}
