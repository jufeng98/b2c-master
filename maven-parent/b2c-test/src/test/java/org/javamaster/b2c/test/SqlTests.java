package org.javamaster.b2c.test;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.test.config.DatabaseConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfiguration.class)
@Transactional
@Sql("classpath:sql-script/actor.sql")
@SqlGroup({
        @Sql(scripts = "classpath:sql-script/actor1.sql", config = @SqlConfig(commentPrefix = "--")),
        @Sql("classpath:sql-script/actor2.sql")
})
public class SqlTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Sql({"classpath:sql-script/actor3.sql"})
    // 新版本Spring才支持
    // @SqlMergeMode(MERGE)
    public void test() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from actor where actor_id > 198");
        log.info("{}", list);
    }

}
