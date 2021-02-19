package org.javamaster.b2c.kafka.runner;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author yudong
 * @date 2019/12/13
 */
@Slf4j
@Component
public class ProducerRunner implements CommandLineRunner {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @SneakyThrows
    @Override
    public void run(String... args) {
        TimeUnit.SECONDS.sleep(6);
       while (true) {
            kafkaTemplate.send("topic_order_code", "TW" + RandomStringUtils.randomNumeric(20));
            TimeUnit.SECONDS.sleep(8);
       }
    }

}
