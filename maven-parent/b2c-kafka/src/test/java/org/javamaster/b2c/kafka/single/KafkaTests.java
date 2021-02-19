package org.javamaster.b2c.kafka.single;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.kafka.config.KafkaConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yudong
 * @date 2020/10/23
 */

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = KafkaConfig.class)
public class KafkaTests {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplateClusterTest;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    public void test() {
        Map<String, Object> map = new HashMap<>(4, 1);
        map.put("id", 8249);
        map.put("topic", "test_topic");
        map.put("key", null);
        map.put("value", "{\"name\":\"liangyudong\"}");
        kafkaTemplateClusterTest.send("topic_order_code", objectMapper.writeValueAsString(map));
        kafkaTemplateClusterTest.flush();
    }


}
