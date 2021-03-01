package org.javamaster.b2c.test;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.test.config.DatabaseConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfiguration.class)
@Transactional
public class SqlScriptsTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScripts(
                new ClassPathResource("sql-script/actor.sql"),
                new ClassPathResource("sql-script/actor1.sql")
        );
        populator.setCommentPrefix("--");
        populator.execute(Objects.requireNonNull(jdbcTemplate.getDataSource()));

        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from actor where actor_id > 198");
        log.info("{}", list);
    }

}
