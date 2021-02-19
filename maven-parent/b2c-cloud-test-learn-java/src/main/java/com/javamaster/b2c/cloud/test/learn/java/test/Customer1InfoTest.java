package com.javamaster.b2c.cloud.test.learn.java.test;

import cn.com.bluemoon.handypoi.excel.enums.ExcelType;
import cn.com.bluemoon.handypoi.excel.resolve.ExcelReader;
import com.javamaster.b2c.cloud.test.learn.java.model.UserAnswerVo;
import com.javamaster.b2c.cloud.test.learn.java.model.UserVo;
import static com.javamaster.b2c.cloud.test.learn.java.test.CustomerInfoTest.SPECIAL_LIST;
import static com.javamaster.b2c.cloud.test.learn.java.test.CustomerInfoTest.UNKNOWN_LIST;
import static com.javamaster.b2c.cloud.test.learn.java.test.CustomerInfoTest.getBirthday;
import static com.javamaster.b2c.cloud.test.learn.java.test.CustomerInfoTest.save;
import com.javamaster.b2c.cloud.test.learn.java.utils.MybatisUtils;
import com.javamaster.b2c.cloud.test.learn.java.utils.OMUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.FileInputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yudong
 * @date 2020/3/23
 */
@Slf4j
public class Customer1InfoTest {
    private static JdbcTemplate jdbcTemplate = MybatisUtils.getJdbcTemplateMoonMall();

    @SneakyThrows
    @Test
    public void test() {
        FileInputStream fileInputStream = new FileInputStream("G://user_answers.xlsx");
        ExcelReader<UserAnswerVo> excelReader = new ExcelReader<>(ExcelType.XLSX, fileInputStream, UserAnswerVo.class, 1, 0);
        excelReader.read();
        List<UserAnswerVo> userAnswerVos = excelReader.getResultList();

        FileInputStream fileInputStream1 = new FileInputStream("G://users.xlsx");
        ExcelReader<UserVo> excelReader1 = new ExcelReader<>(ExcelType.XLSX, fileInputStream1, UserVo.class, 1, 0);
        excelReader1.read();
        List<UserVo> userVos = excelReader1.getResultList();
        Map<Integer, UserVo> map = userVos.stream().collect(Collectors.toMap(UserVo::getId, userVo -> userVo));

        for (UserAnswerVo userAnswerVo : userAnswerVos) {
            JsonNode jsonNode = OMUtils.objectMapper.readValue(userAnswerVo.getAnswer(), JsonNode.class);
            UserVo userVo = map.get(userAnswerVo.getUserId());
            if (userVo == null) {
                log.error("error userId:{}", userAnswerVo.getUserId());
                continue;
            }
            String mobile = userVo.getPhone();

            String userId;
            try {
                Map<String, Object> dbUserMap = jdbcTemplate.queryForMap("select * from mall_user_base where mobile=?", mobile);
                userId = (String) dbUserMap.get("user_id");
            } catch (Exception e) {
                log.error("error moble:{}", mobile);
                continue;
            }
            handlerData(jsonNode, Long.parseLong(mobile), userId);
        }
    }

    public static void handlerData(JsonNode dataNode, Long mobile, String userId) {
        Map<String, Pair> fieldsMap = Maps.newHashMap();
        Iterator<Map.Entry<String, JsonNode>> iterator = dataNode.fields();
        boolean handWash = false;
        boolean machineWash = false;
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = iterator.next();
            String type = entry.getKey();
            JsonNode subDataNode = entry.getValue();
            if (SPECIAL_LIST.contains(type)) {
                String value = subDataNode.get("value").asText("");
                String other = subDataNode.get("other").asText("");
                if (StringUtils.isBlank(value) && StringUtils.isBlank(other)) {
                    fieldsMap.put(type, Pair.of("", -1));
                } else {
                    if (!"other".equals(value)) {
                        fieldsMap.put(type, Pair.of("", value));
                    } else {
                        if (StringUtils.isNotBlank(other)) {
                            fieldsMap.put(type, Pair.of("", other));
                        } else {
                            fieldsMap.put(type, Pair.of("", -1));
                        }
                    }
                }
            } else if ("cook_frequency".equals(type)) {
                String value = subDataNode.asText();
                if ("0.33".equals(value)) {
                    fieldsMap.put(type, Pair.of("", 3));
                } else if ("0.5".equals(type)) {
                    fieldsMap.put(type, Pair.of("", 2));
                } else if ("1".equals(type)) {
                    fieldsMap.put(type, Pair.of("", 1));
                } else if ("0".equals(type)) {
                    fieldsMap.put(type, Pair.of("", 0));
                } else {
                    fieldsMap.put(type, Pair.of("", -1));
                }
            } else if ("baby_clothes_wash_frequency".equals(type)) {
                String value = subDataNode.asText();
                if ("0.33".equals(value)) {
                    fieldsMap.put(type, Pair.of("", 1));
                } else if ("0.5".equals(type)) {
                    fieldsMap.put(type, Pair.of("", 2));
                } else if ("1".equals(type)) {
                    fieldsMap.put(type, Pair.of("", 3));
                } else if ("2".equals(type)) {
                    fieldsMap.put(type, Pair.of("", 4));
                } else if ("3".equals(type)) {
                    fieldsMap.put(type, Pair.of("", 5));
                } else {
                    fieldsMap.put(type, Pair.of("", -1));
                }
            } else if ("wash_hand_frequency".equals(type)) {
                String value = subDataNode.asText();
                if ("0.33".equals(value) || "0.25".equals(value)) {
                    fieldsMap.put(type, Pair.of("", 3));
                } else if ("0.5".equals(type)) {
                    fieldsMap.put(type, Pair.of("", 2));
                } else if ("1".equals(type)) {
                    fieldsMap.put(type, Pair.of("", 1));
                } else if ("0".equals(type)) {
                    fieldsMap.put(type, Pair.of("", 0));
                } else {
                    fieldsMap.put(type, Pair.of("", -1));
                }
            } else if (UNKNOWN_LIST.contains(type)) {
                // do nothing
            } else if ("is_hand_wash".contains(type)) {
                String value = subDataNode.asText();
                if ("1".equals(value)) {
                    handWash = true;
                }
            } else if ("is_machine_wash".contains(type)) {
                String value = subDataNode.asText();
                if ("1".equals(value)) {
                    machineWash = true;
                }
            } else {
                String value = subDataNode.asText();
                if (StringUtils.isNotBlank(value)) {
                    fieldsMap.put(type, Pair.of("", value));
                } else {
                    fieldsMap.put(type, Pair.of("", -1));
                }
            }
        }
        if (handWash && machineWash) {
            fieldsMap.put("wash_type", Pair.of("", 3));
        } else if (handWash) {
            fieldsMap.put("wash_type", Pair.of("", 2));
        } else {
            fieldsMap.put("wash_type", Pair.of("", 1));
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
}
