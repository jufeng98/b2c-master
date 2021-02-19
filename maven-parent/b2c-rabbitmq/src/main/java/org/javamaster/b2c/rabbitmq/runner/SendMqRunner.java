package org.javamaster.b2c.rabbitmq.runner;

import org.javamaster.b2c.rabbitmq.constant.JmsConst;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author yudong
 * @date 2020/8/19
 */
@Component
public class SendMqRunner implements CommandLineRunner {
    private Random random = new Random();

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public void run(String... args) {
        new Thread(() -> {
            while (true) {
                String bookUser = random.nextInt() + "yu";
                String orderNo = "TW" + random.nextInt();
                Map<String, String> map = new HashMap<>(2, 1);
                map.put("bookUser", bookUser);
                map.put("orderNo", orderNo);
                rabbitTemplate.convertAndSend("fanoutExchange", JmsConst.ORDER_ROUTING_TOPIC_KEY, map);
                rabbitTemplate.convertAndSend("topicExchange", JmsConst.ORDER_ROUTING_TOPIC_KEY, map);
                rabbitTemplate.convertAndSend("directExchange", JmsConst.ORDER_ROUTING_DIRECT_KEY, map);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException ignored) {
                }
            }
        }).start();
    }
}
