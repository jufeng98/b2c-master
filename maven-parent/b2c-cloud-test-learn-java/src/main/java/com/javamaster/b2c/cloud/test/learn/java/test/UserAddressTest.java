package com.javamaster.b2c.cloud.test.learn.java.test;

import com.javamaster.b2c.cloud.test.learn.java.jdbc.model.MallUserAddress;
import com.javamaster.b2c.cloud.test.learn.java.utils.MybatisUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yudong
 * @date 2020/2/24
 */
@Slf4j
public class UserAddressTest {

    private static ExecutorService executorService = Executors.newScheduledThreadPool(10);
    private static JdbcTemplate jdbcTemplate = MybatisUtils.getJdbcTemplateMoonMall();

    @Test
    @SneakyThrows
    public void test() {
        log.info("start time:{}", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        Date date = DateUtils.parseDate("2018-09-24 21:00:00", "yyyy-MM-dd HH:mm:ss");
        List<MallUserAddress> list = jdbcTemplate.query("select * from mall_user_address where EXCEPTION_FLAG is null and status = 1", new Object[]{}, (rs, rowNum) -> convert(rs));
        handlerList(list);
    }

    private MallUserAddress convert(ResultSet rs) throws SQLException {
        MallUserAddress mallUserAddress = new MallUserAddress();
        mallUserAddress.setAddressId(rs.getString("ADDRESS_ID"));
        mallUserAddress.setProvinceName(rs.getString("PROVINCE_NAME"));
        mallUserAddress.setCityName(rs.getString("CITY_NAME"));
        mallUserAddress.setCountyName(rs.getString("COUNTY_NAME"));
        mallUserAddress.setStreetName(StringUtils.defaultString(rs.getString("STREET_NAME")));
        mallUserAddress.setVillageName(StringUtils.defaultString(rs.getString("VILLAGE_NAME")));
        mallUserAddress.setAddress(rs.getString("ADDRESS"));
        return mallUserAddress;
    }

    private void handlerList(List<MallUserAddress> list) throws InterruptedException {
        log.info("list size:{}", list.size());
        int start = 0;
        int end = 10;
        while (end < list.size()) {
            List<MallUserAddress> subList = list.subList(start, end);
            executorService.submit(new UserAddressTask(subList, jdbcTemplate));
            start = end;
            end += 10;
        }
        List<MallUserAddress> subList = list.subList(start, list.size());
        executorService.submit(new UserAddressTask(subList, jdbcTemplate));
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
    }

}
