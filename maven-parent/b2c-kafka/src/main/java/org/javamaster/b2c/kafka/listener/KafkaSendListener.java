package org.javamaster.b2c.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

/**
 * @author yudong
 * @date 2020/6/11
 */
@Slf4j
@Component
public class KafkaSendListener implements ProducerListener<Object, Object> {

    @Override
    public void onSuccess(String topic, Integer partition, Object key, Object value, RecordMetadata recordMetadata) {
        log.info("success topic:{},key:{},value:{}", topic, key, value);
    }

    @Override
    public void onError(String topic, Integer partition, Object key, Object value, Exception exception) {
        log.info("error topic:{},key:{},value:{}", topic, key, value, exception);
    }

}
