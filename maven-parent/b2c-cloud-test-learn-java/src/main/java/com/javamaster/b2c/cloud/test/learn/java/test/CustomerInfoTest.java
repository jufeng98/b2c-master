package com.javamaster.b2c.cloud.test.learn.java.test;

import com.javamaster.b2c.cloud.test.learn.java.model.MallAdviserCustomerInfo;
import com.javamaster.b2c.cloud.test.learn.java.utils.MybatisUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author yudong
 * @date 2020/3/10
 */
@Slf4j
public class CustomerInfoTest {
    private static JdbcTemplate jdbcTemplate = MybatisUtils.getJdbcTemplateMoonMall();
    private static RestTemplate restTemplate = new RestTemplate();

    @Test
    public void test() {
        List<MallAdviserCustomerInfo> list = jdbcTemplate.query("select * from mall_adviser_customer_info",
                new Object[]{},
                (rs, rowNum) -> convert(rs));
        list = new ArrayList<>(new HashSet<>(list));
        handlerList(list);
    }

    public static MallAdviserCustomerInfo convert(ResultSet rs) throws SQLException {
        MallAdviserCustomerInfo info = new MallAdviserCustomerInfo();
        info.setId(rs.getLong("id"));
        info.setMobile(rs.getLong("mobile"));
        info.setUserId(rs.getString("user_id"));
        info.setUserName(rs.getString("user_name"));
        info.setAngelCode(rs.getString("angel_code"));
        info.setLabelType(rs.getString("label_type"));
        info.setAutonym(rs.getBoolean("autonym"));
        info.setBinging(rs.getInt("binging"));
        info.setRegisterTime(rs.getDate("register_time"));
        info.setBingingTime(rs.getDate("binging_time"));
        return info;
    }

    public static final List<String> SPECIAL_LIST = Arrays.asList("machine_wash_frequency", "hand_wash_frequency", "preprocessing_frequency");
    public static final Map<String, Integer> SPECIAL_MAP = Maps.newLinkedHashMap();
    public static final Pattern PATTERN = Pattern.compile("[0-9]+");
    public static final List<String> UNKNOWN_LIST = Arrays.asList("disturb", "is_used_to_sterilize", "is_need_softener", "is_stain_cleaner", "adult_numbers");

    static {
        SPECIAL_MAP.put("每天", 1);
        SPECIAL_MAP.put("两天一次", 2);
        SPECIAL_MAP.put("三天一次", 3);
        SPECIAL_MAP.put("七天一次", 7);
        SPECIAL_MAP.put("", -1);
    }

    private void handlerList(List<MallAdviserCustomerInfo> list) {
        String url = "https://app4.bluemoon.com.cn/api/user/answer?phone={1}";
        outer:
        for (MallAdviserCustomerInfo info : list) {
            JsonNode jsonNode;
            try {
                jsonNode = restTemplate.getForObject(url, JsonNode.class, info.getMobile());
                log.info("mobile:{},res:{}", info.getMobile(), jsonNode.toString());
            } catch (HttpClientErrorException e) {
                log.error("error mobile:{},userId:{},res:{}", info.getMobile(), info.getUserId(), e.getResponseBodyAsString());
                continue;
            }
            if (jsonNode.get("code").asInt(-1) != 200) {
                log.error("error mobile:{},userId:{},res1:{}", info.getMobile(), info.getUserId(), jsonNode.toString());
                continue;
            }
            JsonNode dataNode = jsonNode.get("data");
            handlerData(dataNode, info.getMobile(), info.getUserId());
        }
    }

    public static void handlerData(JsonNode dataNode, Long mobile, String userId) {
        Map<String, Pair> fieldsMap = Maps.newHashMap();
        Iterator<Map.Entry<String, JsonNode>> iterator = dataNode.fields();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = iterator.next();
            String type = entry.getKey();
            String label = entry.getValue().asText();
            if (SPECIAL_LIST.contains(type)) {
                Integer value = SPECIAL_MAP.get(label);
                if (value != null) {
                    fieldsMap.put(type, Pair.of(label, value));
                } else {
                    String tmpLabel = label.replace("天/次", "");
                    if (PATTERN.matcher(tmpLabel).find()) {
                        fieldsMap.put(type, Pair.of(label, Integer.parseInt(tmpLabel)));
                    } else {
                        log.error("field illegal,error mobile:{},type:{},label:{}", mobile, type, label);
                    }
                }
            } else {
                if (StringUtils.isNotBlank(label)) {
                    try {
                        Map<String, Object> dictMap = jdbcTemplate.queryForMap("select * from sys_dict where type=? and label=?", under2camel(type), label);
                        byte value = Byte.parseByte(dictMap.get("value").toString());
                        fieldsMap.put(type, Pair.of(label, value));
                    } catch (Exception e) {
                        log.error("dict not found,error mobile:{},userId:{},type:{},label:{}", mobile, userId, type, label);
                        return;
                    }
                } else {
                    fieldsMap.put(type, Pair.of(label, -1));

                }
            }
        }
        fieldsMap.put("user_id", Pair.of("user_id", "'" + userId + "'"));
        fieldsMap.put("work_industry", Pair.of("work_industry", -1));
        fieldsMap.put("room_numbers", Pair.of("room_numbers", -1));
        fieldsMap.put("hall_numbers", Pair.of("hall_numbers", -1));
        fieldsMap.put("kitchen_numbers", Pair.of("kitchen_numbers", -1));
        fieldsMap.put("rest_room_numbers", Pair.of("rest_room_numbers", -1));
        fieldsMap.put("year_income", Pair.of("year_income", -1));
        fieldsMap.put("clear_year_consume", Pair.of("clear_year_consume", -1));
        try {
            String birthday = getBirthday(userId);
            fieldsMap.put("birthday", Pair.of("birthday", "'" + birthday + "'"));
        } catch (Exception e) {
            log.error("birthday not found,error mobile:{},userId:{}", mobile, userId);
        }
        log.info("fields map:{}", fieldsMap);
        String fields = StringUtils.join(fieldsMap.keySet(), ",");
        Collection<Pair> collection = fieldsMap.values();
        String values = StringUtils.join(collection.stream().map(Pair::getRight).collect(Collectors.toList()), ",");
        String sql = String.format("insert into mall_adviser_customer_additional_info(%s) values (%s)", fields, values);
        log.info("insert sql:{}", sql);
        save(userId, sql);
    }


    public static String under2camel(String s) {
        String separator = "_";
        StringBuilder under = new StringBuilder();
        s = s.toLowerCase().replace(separator, " ");
        String sarr[] = s.split(" ");
        for (int i = 0; i < sarr.length; i++) {
            String w = sarr[i].substring(0, 1).toUpperCase() + sarr[i].substring(1);
            under.append(w);
        }
        under.replace(0, 1, String.valueOf(under.charAt(0)).toLowerCase());
        return under.toString();
    }

    public static void save(String userId, String insertSql) {
        String sql = "select count(*) num from mall_adviser_customer_additional_info where user_id=?";
        Long num = (Long) jdbcTemplate.queryForMap(sql, userId).get("num");
        if (num == 0) {
            jdbcTemplate.execute(insertSql);
        } else {
            log.info("userId exists:{}", userId);
        }
    }

    public static String getBirthday(String userId) {
        String sql = "select * from mall_user_name_auth where user_id=?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, userId);
        String cardId = (String) map.get("card_id");
        return extractYearMonthDayOfIdCard(cardId);
    }

    public static String extractYearMonthDayOfIdCard(String cardId) {
        String year;
        String month;
        String day;
        if (cardId.length() == 15) {
            year = "19" + cardId.substring(6, 8);
            month = cardId.substring(8, 10);
            day = cardId.substring(10, 12);
            return year + "-" + month + "-" + day;
        } else {
            year = cardId.substring(6, 10);
            month = cardId.substring(10, 12);
            day = cardId.substring(12, 14);
            return year + "-" + month + "-" + day;
        }
    }
}
