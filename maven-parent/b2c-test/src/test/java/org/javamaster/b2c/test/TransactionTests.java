package org.javamaster.b2c.test;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.test.config.DatabaseConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfiguration.class)
@Transactional
public class TransactionTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeTransaction
    void beforeTransaction() {
        log.info("beforeTransaction");
    }

    @AfterTransaction
    void afterTransaction() {
        log.info("afterTransaction");
    }

    @Test
    public void test() {
        int rows = JdbcTestUtils.deleteFromTableWhere(jdbcTemplate, "actor", "actor_id=?", 198);
        log.info("rows:{}", rows);
    }

}
