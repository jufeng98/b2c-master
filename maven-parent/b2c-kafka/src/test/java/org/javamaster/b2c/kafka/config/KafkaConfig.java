package org.javamaster.b2c.kafka.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.javamaster.b2c.config.B2cMasterConsts;
import org.springframework.context.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.ProducerListener;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Slf4j
@Configuration
public class KafkaConfig {

    @Bean
    @Lazy
    public KafkaTemplate<Object, Object> kafkaTemplateClusterTest() {
        Map<String, Object> configs = new HashMap<>(20, 1);
        configs.put("bootstrap.servers", B2cMasterConsts.Server.KAFKA_CLUSTER);
        configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configs.put("group.id", "wx_card_group");
        configs.put("enable.auto.commit", "true");
        configs.put("auto.commit.interval", "100");
        configs.put("auto.offset.reset", "earliest");
        configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configs.put("buffer.memory", "33554432");
        configs.put("batch.size", "16384");
        configs.put("retries", "3");
        configs.put("acks", "1");
        ProducerFactory<Object, Object> kafkaProducerFactory = new DefaultKafkaProducerFactory<>(configs);
        KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate<>(kafkaProducerFactory);
        kafkaTemplate.setProducerListener(new ProducerListener<Object, Object>() {
            @Override
            public void onSuccess(String topic, Integer partition, Object key, Object value, RecordMetadata recordMetadata) {
                log.info("{}", "success:" + value);
            }

            @Override
            public void onError(String topic, Integer partition, Object key, Object value, Exception exception) {
                log.info("{}", "error:" + value);
            }
        });
        return kafkaTemplate;
    }

    @Bean
    @Lazy
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
