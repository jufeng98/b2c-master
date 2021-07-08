package com.javamaster.b2c.cloud.test.learn.java.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import com.javamaster.b2c.cloud.test.learn.java.utils.MybatisUtils;
import static com.javamaster.b2c.cloud.test.learn.java.utils.PropertiesUtils.getProp;
import static java.util.stream.Collectors.toList;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2018/12/21.<br/>
 *
 * @author yudong
 */
public class HandlerWashingRecordTest {

    @Test
    public void test() {


        DruidDataSource moonmallDataSource = MybatisUtils.getDatasourceJdbcMoonMall();

        DruidDataSource appManagerDataSource = new DruidDataSource();
        appManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        appManagerDataSource.setUrl(getProp("Honor.URL_APP"));
        appManagerDataSource.setUsername(getProp("WashingService.USERNAME"));
        appManagerDataSource.setPassword(getProp("WashingService.PASSWORD"));
        // appManagerDataSource.setUrl(URL_APP_1);
        // appManagerDataSource.setUsername(USERNAME);
        // appManagerDataSource.setPassword(PASSWORD);
        appManagerDataSource.setInitialSize(10);
        appManagerDataSource.setMaxActive(20);
        appManagerDataSource.setMaxWait(10000);
        appManagerDataSource.setDefaultAutoCommit(true);
        appManagerDataSource.setRemoveAbandoned(true);
        appManagerDataSource.setTestOnBorrow(true);
        appManagerDataSource.setValidationQuery("select now()");

        JdbcTemplate moonmallJdbcTemplate = new JdbcTemplate(moonmallDataSource);
        JdbcTemplate appManagerJdbcTemplate = new JdbcTemplate(appManagerDataSource);

        String washOrderSql = "select  id,order_id,coupon_code from mall_order_promotion \n" +
                "where id > '18122416000000000000' \n" +
                "and id < '18122499999999999999'\n" +
                "and order_id not like 'O%' \n" +
                "and coupon_code is not null;";
        System.out.println(washOrderSql);

        List<Map<String, Object>> washOrderMapList = moonmallJdbcTemplate.queryForList(washOrderSql);
        System.out.println(JSONObject.toJSONString(washOrderMapList));

        List<String> updateList = washOrderMapList.stream().map(map -> {
            String orderId = ((String) map.get("order_id"));
            String couponCode = ((String) map.get("coupon_code"));

            String idSql = "select coupon_id,coupon_code from mall_user_coupon_package where coupon_code='%s'";
            idSql = String.format(idSql, couponCode);
            Map<String, Object> idMap = moonmallJdbcTemplate.queryForMap(idSql);

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String sNameSql = "select coupon_id,coupon_code,coupon_s_name from mall_promotion_coupon_info where coupon_id='%s'";
            sNameSql = String.format(sNameSql, (idMap.get("coupon_id")));
            Map<String, Object> sNameMap = moonmallJdbcTemplate.queryForMap(sNameSql);

            String couponSName = ((String) sNameMap.get("coupon_s_name"));

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String outerCodeSql = "select id,outer_code,outer_status from mall_wash_order_info where id='%s'";
            outerCodeSql = String.format(outerCodeSql, orderId);
            Map<String, Object> outerCodeMap = appManagerJdbcTemplate.queryForMap(outerCodeSql);

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String updateSql = "update mall_wash_order_info set coupon_code='%s',coupon_s_name='%s' where outer_code='%s';";
            updateSql = String.format(updateSql, couponCode, couponSName, outerCodeMap.get("outer_code"));
            return updateSql;
        }).collect(toList());

        updateList.forEach(System.out::println);
    }
}
