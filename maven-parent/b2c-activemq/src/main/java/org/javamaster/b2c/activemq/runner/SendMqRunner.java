package org.javamaster.b2c.activemq.runner;

import org.javamaster.b2c.activemq.constant.JmsConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
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
    private JmsTemplate jmsTemplate;


    @Override
    public void run(String... args) {
        new Thread(() -> {
            while (true) {
                String bookUser = random.nextInt() + "yu";
                String orderNo = "TW" + random.nextInt();
                Map<String, String> map = new HashMap<>(2, 1);
                map.put("bookUser", bookUser);
                map.put("orderNo", orderNo);
                jmsTemplate.convertAndSend(JmsConst.ACTIVE_MQ_QUEUE, map);
                jmsTemplate.convertAndSend(JmsConst.ACTIVE_MQ_TOPIC, map);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException ignored) {
                }
            }
        }).start();
    }
}
