package org.javamaster.b2c.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author yudong
 * @date 2019/12/13
 */
@Slf4j
@Component
public class Consumer {

    private int anInt = 0;

    @KafkaListener(groupId = "test_group", topics = "topic_order_code", errorHandler = "kafkaErrorHandler")
    public void handler(ConsumerRecord<Object, Object> consumerRecord) {
        try {
            TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(3000));
        } catch (InterruptedException ignored) {
        }
        log.info("consumer times:{},{},{}", anInt++, consumerRecord.key(), consumerRecord.value());
        // if (anInt < 16) {
        //     // 若消费失败,则消息会重新回到kafka(未指定errorHandler的情况下)
        //     throw new RuntimeException("模拟异常");
        // }
    }

}
