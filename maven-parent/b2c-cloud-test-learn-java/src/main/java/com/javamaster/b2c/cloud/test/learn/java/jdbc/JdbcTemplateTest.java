package com.javamaster.b2c.cloud.test.learn.java.jdbc;

import com.alibaba.fastjson.JSONObject;
import com.javamaster.b2c.cloud.test.learn.java.model.Actor;
import com.javamaster.b2c.cloud.test.learn.java.utils.MybatisUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yudong
 * @date 2019/5/9
 */
@Slf4j
public class JdbcTemplateTest {

    static JdbcTemplate jdbcTemplate;

    @BeforeClass
    public static void init() {
        jdbcTemplate = MybatisUtils.getJdbcTemplateSakila();
    }

    @Test
    public void test() {
        Date o = jdbcTemplate.queryForObject("select now() time", Date.class);
        log.info("{}", o);
    }

    @Test
    public void test1() {
        List<Actor> o = jdbcTemplate.query("select * from actor limit 10", rs -> {
            List<Actor> actors = Lists.newArrayList();
            while (rs.next()) {
                actors.add(getActor(rs));
            }
            return actors;
        });
        log.info("{}", JSONObject.toJSONString(o, true));
    }

    @Test
    public void test2() {
        List<Actor> o = jdbcTemplate.query("select * from actor limit 10", (rs, rowNum) -> getActor(rs));
        log.info("{}", JSONObject.toJSONString(o, true));
    }

    @Test
    public void test3() {
        Map<String, Object> o = jdbcTemplate.queryForMap("select * from actor limit 1");
        log.info("{}", JSONObject.toJSONString(o, true));
    }

    @Test
    public void test4() {
        Actor o = jdbcTemplate.queryForObject("select * from actor limit 1", (rs, rowNum) -> getActor(rs));
        log.info("{}", JSONObject.toJSONString(o, true));
    }

    @Test
    public void test5() {
        List<Map<String, Object>> o = jdbcTemplate.queryForList("select * from actor limit 10");
        log.info("{}", JSONObject.toJSONString(o, true));
    }

    @Test
    public void test6() {
        List<Date> o = jdbcTemplate.queryForList("select last_update from actor limit 10", Date.class);
        log.info("{}", JSONObject.toJSONString(o, true));
    }

    @Test
    public void test7() {
        List<Actor> o = jdbcTemplate.query("select * from actor where first_name=?", new Object[]{"ADAM"}, (rs, rowNum) -> getActor(rs));
        log.info("{}", JSONObject.toJSONString(o, true));
    }

    @Test
    public void test8() {
        List<Actor> o = jdbcTemplate.query("select * from actor where first_name=?", (rs, rowNum) -> getActor(rs), "ADAM");
        log.info("{}", JSONObject.toJSONString(o, true));
    }

    @Test
    public void test10() {
        Actor o = jdbcTemplate.queryForObject("select * from actor where first_name=? and last_name=?", (rs, rowNum) -> getActor(rs), "ADAM", "GRANT");
        log.info("{}", JSONObject.toJSONString(o, true));
    }

    @Test
    public void testBatchInsert() {
        long start = System.currentTimeMillis();
        String sql = "insert into people(first_name,last_name) values(?,?)";
        List<Object[]> args = IntStream.range(0, 200000).mapToObj(i -> generatePeople()).collect(Collectors.toList());
        int[][] res = jdbcTemplate.batchUpdate(sql, args, 50000, (ps, argument) -> {
            ps.setString(1, (String) argument[0]);
            ps.setString(2, (String) argument[1]);
        });
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(res);
    }

    @Test
    public void testBatchUpdate() {
        long start = System.currentTimeMillis();
        String sql = "update people set first_name=? where person_id=?";
        List<Object[]> args = IntStream.range(0, 200000).mapToObj(i -> generateUpdatePeople(i + 270206)).collect(Collectors.toList());
        int[][] res = jdbcTemplate.batchUpdate(sql, args, 50000, (ps, argument) -> {
            ps.setString(1, (String) argument[0]);
            ps.setInt(2, (int) argument[1]);
        });
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(res);
    }

    private Object[] generatePeople() {
        return new Object[]{RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(5)};
    }

    private Object[] generateUpdatePeople(int id) {
        return new Object[]{"yudong", id};
    }

    private Actor getActor(ResultSet rs) throws SQLException {
        Actor actor = new Actor();
        actor.setActorId(rs.getInt("actor_id"));
        actor.setFirstName(rs.getString("first_name"));
        actor.setLastName(rs.getString("last_name"));
        actor.setLastUpdate(rs.getTimestamp("last_update"));
        return actor;
    }
}
