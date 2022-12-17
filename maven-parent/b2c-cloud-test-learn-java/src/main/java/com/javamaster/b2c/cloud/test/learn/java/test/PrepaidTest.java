package com.javamaster.b2c.cloud.test.learn.java.test;

import com.alibaba.fastjson.JSONObject;
import com.javamaster.b2c.cloud.test.learn.java.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yudong
 * @date 2019/7/11
 */
public class PrepaidTest {
    @Test
    public void chargePrepaid() {
        String mobile = "13800138000";
        JdbcTemplate jdbcTemplate = MybatisUtils.getJdbcTemplateMoonMall();
        String sql = "select * from mall_user_base where mobile = ?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, mobile);
        System.out.println(JSONObject.toJSONString(map, true));
        String userId = (String) map.get("USER_ID");
        System.out.println(userId);
        if (StringUtils.isBlank(userId)) {
            return;
        }
        sql = "select * from mall_user_prepaid_package where user_id = ? and state = ?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, userId, "wait_pay");
        System.out.println(JSONObject.toJSONString(list, true));
        List<String> prepaidIds = list.stream()
                .filter(mapObj -> {
                    String prepaidId = (String) mapObj.get("prepaid_id");
                    return StringUtils.isNotBlank(prepaidId);
                })
                .map(mapObj -> {
                    String prepaidId = (String) mapObj.get("prepaid_id");
                    return prepaidId;
                })
                .collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(prepaidIds, true));
        if (prepaidIds.isEmpty()) {
            return;
        }
        sql = "update mall_user_prepaid_package set state = ?,buy_time = ? where prepaid_id = ? and user_id = ?";
        for (String prepaidId : prepaidIds) {
            int affect = jdbcTemplate.update(sql, "payed", new Date(), prepaidId, userId);
            System.out.println(affect);
        }
    }
}
