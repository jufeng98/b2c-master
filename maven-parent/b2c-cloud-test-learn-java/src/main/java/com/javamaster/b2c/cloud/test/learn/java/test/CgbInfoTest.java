package com.javamaster.b2c.cloud.test.learn.java.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.javamaster.b2c.cloud.test.learn.java.model.CgbAngelInfo;
import com.javamaster.b2c.cloud.test.learn.java.utils.MybatisUtils;
import static com.javamaster.b2c.cloud.test.learn.java.utils.PropertiesUtils.getProp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author yudong
 * @date 2020/3/10
 */
@Slf4j
public class CgbInfoTest {
    private static JdbcTemplate jdbcTemplate = MybatisUtils.getJdbcTemplateB2b2cMall();
    private static RestTemplate restTemplate = new RestTemplate();

    Logger logger = LoggerFactory.getLogger("sql.original.CdbInfoTest");
    Logger logger1 = LoggerFactory.getLogger("sql.new.CdbInfoTest");

    @Test
    public void test() {
        List<CgbAngelInfo> list = jdbcTemplate.query("select * from cgb_angel_info where op_time >= '2020-03-30 00:00:00' and created_time<'2020-03-30 00:00:00'",
                new Object[]{},
                (rs, rowNum) -> convert(rs));
        list = new ArrayList<>(new HashSet<>(list));
        handlerList(list);
    }

    private CgbAngelInfo convert(ResultSet rs) throws SQLException {
        CgbAngelInfo info = new CgbAngelInfo();
        info.setId(rs.getLong("id"));
        info.setUserid(rs.getString("userid"));
        info.setStatus(rs.getInt("status"));
        info.setAngelId(rs.getLong("angel_id"));
        info.setAngelName(rs.getString("angel_name"));
        info.setAngelNo(rs.getString("angel_no"));
        info.setLongitude(rs.getString("longitude"));
        info.setLatitude(rs.getString("latitude"));
        info.setValid(rs.getInt("valid"));
        info.setProvinceName(rs.getString("province_name"));
        info.setCityName(rs.getString("city_name"));
        info.setCountyName(rs.getString("county_name"));
        info.setReceiveAddrName(rs.getString("receive_addr_name"));
        info.setReceiveAddr(rs.getString("receive_addr"));
        return info;
    }

    private void handlerList(List<CgbAngelInfo> list) {
        String url = "http://restapi.amap.com/v3/assistant/coordinate/convert?locations={1},{2}&coordsys=baidu&output=JSON&key=" + getProp("Map.GAODE_KEY_2");
        for (CgbAngelInfo cgbAngelInfo : list) {
            if (StringUtils.isBlank(cgbAngelInfo.getLongitude())) {
                log.info("address blank userId:{}", cgbAngelInfo.getUserid());
                continue;
            }
            JsonNode jsonNode = restTemplate.getForObject(url, JsonNode.class, cgbAngelInfo.getLongitude(), cgbAngelInfo.getLatitude());
            log.info("req:{},res:{}", cgbAngelInfo.getUserid(), jsonNode);
            if (!"ok".equals(jsonNode.get("info").asText())) {
                log.error("wrong:{},res:{}", cgbAngelInfo.getUserid(), jsonNode);
                continue;
            }
            String locations = jsonNode.get("locations").asText();
            if (StringUtils.isBlank(locations)) {
                log.error("wrong1:{},res:{}", cgbAngelInfo.getUserid(), jsonNode);
                continue;
            }
            String[] strs = locations.split(",");
            log.info("id:{},userId:{},olng:{},olat:{}", cgbAngelInfo.getId(), cgbAngelInfo.getUserid(), cgbAngelInfo.getLongitude(), cgbAngelInfo.getLatitude());
            log.info("id:{},userId:{},lng:{},lat:{}", cgbAngelInfo.getId(), cgbAngelInfo.getUserid(), strs[0], strs[1]);
            String originalSql = "update cgb_angel_info set longitude='%s',latitude='%s' where id=%s";
            originalSql = String.format(originalSql, cgbAngelInfo.getLongitude(), cgbAngelInfo.getLatitude(), cgbAngelInfo.getId());
            logger.info("{}", originalSql);
            String sql = "update cgb_angel_info set longitude='%s',latitude='%s' where id=%s";
            sql = String.format(sql, strs[0], strs[1], cgbAngelInfo.getId());
            logger1.info("{}", sql);
            jdbcTemplate.execute(sql);
        }
    }


    @Test
    public void test1() {
        List<CgbAngelInfo> list = jdbcTemplate.query("select * from cgb_angel_info where longitude is null and province_name is not null and created_time >= '2020-03-30 00:00:00'",
                new Object[]{},
                (rs, rowNum) -> convert(rs));
        list = new ArrayList<>(new HashSet<>(list));
        handlerBlank(list);
    }

    private void handlerBlank(List<CgbAngelInfo> list) {
        String url = "https://restapi.amap.com/v3/geocode/geo";
        String key = getProp("Map.GAODE_KEY_2");
        outer:
        for (CgbAngelInfo cgbAngelInfo : list) {
            String address;
            JsonNode jsonNode;
            if (cgbAngelInfo.getReceiveAddr().contains("省")) {
                address = cgbAngelInfo.getReceiveAddr();
                String params = "key=" + key + "&output=JSON" + "&address=" + address;
                jsonNode = restTemplate.getForObject(url + "?" + params, JsonNode.class);
            } else {
                address = cgbAngelInfo.getProvinceName() + cgbAngelInfo.getCityName() + cgbAngelInfo.getCountyName();
                if (StringUtils.isBlank(cgbAngelInfo.getReceiveAddrName())) {
                    address += cgbAngelInfo.getReceiveAddr();
                } else {
                    address += cgbAngelInfo.getReceiveAddrName();
                }
                String params = "key=" + key + "&output=JSON" + "&address=" + address;
                jsonNode = restTemplate.getForObject(url + "?" + params, JsonNode.class);
            }
            logger.info("id:{},address:{}", cgbAngelInfo.getId(), address);
            if (!"OK".equals(jsonNode.get("info").asText())) {
                log.error("wrong:{},res:{}", cgbAngelInfo.getUserid(), jsonNode);
                continue;
            }
            ArrayNode jsonArray = (ArrayNode) jsonNode.get("geocodes");
            String location = jsonArray.get(0).get("location").asText();
            if (StringUtils.isBlank(location)) {
                log.error("wrong:{},res:{}", cgbAngelInfo.getUserid(), jsonNode);
                continue outer;
            }
            String[] strs = location.split(",");
            String sql = "update cgb_angel_info set longitude='%s',latitude='%s' where id=%s";
            sql = String.format(sql, strs[0], strs[1], cgbAngelInfo.getId());
            logger1.info("{}", sql);
            jdbcTemplate.execute(sql);
        }

    }
}
