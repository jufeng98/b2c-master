package com.javamaster.b2c.cloud.test.learn.java.jdbc;

import com.javamaster.b2c.cloud.test.learn.java.utils.MybatisUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Iterator;
import java.util.List;

/**
 * Created on 2018/12/21.<br/>
 *
 * @author yudong
 */
public class HandlerWashMallRecordTest {
    public static void main(String[] args) {
        String sql = "select user_id,outer_code from mall_wash_order_info limit 100";
        JdbcTemplate jdbcTemplate = MybatisUtils.getJdbcTemplateWashMall();
        List<Pair> list = jdbcTemplate.query(sql,
                (rs, rowNum) -> Pair.of(rs.getString("user_id"), rs.getString("outer_code")));
        Iterator<Pair> iterator = list.iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getLeft() == null || pair.getRight() == null) {
                continue;
            }
            System.out.println(pair);
        }
    }
}
