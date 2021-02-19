package org.javamaster.b2c.mybatis.single;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.mybatis.config.MybatisConfig;
import org.javamaster.b2c.mybatis.service.TestService;
import org.javamaster.b2c.mybatis.service.impl.TestServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.*;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        MybatisConfig.class,
        TestServiceImpl.class
})
public class MybatisTests {

    @Autowired
    private TestService testService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    @Test
    @Transactional
    @Sql("classpath:sql-script/actor.sql")
    public void selectActors() {
        List<Map<String, String>> maps = testService.selectActors(Collections.singletonList("1"));
        log.info("{}", maps);
    }

    @Test
    @Transactional
    public void test() {
        log.info("{}", JdbcTestUtils.countRowsInTable(jdbcTemplate, "actor"));
    }

    @Test
    @Transactional
    public void databaseTest() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScripts(new ClassPathResource("sql-script/actor.sql"));
        populator.execute(dataSource);
        log.info("{}", JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "actor", "actor_id=1"));
    }

}
