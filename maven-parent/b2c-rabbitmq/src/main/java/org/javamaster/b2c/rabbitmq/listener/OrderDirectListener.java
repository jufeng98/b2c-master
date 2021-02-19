package org.javamaster.b2c.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yudong
 */
@Component
@RabbitListener(queues = "directQueue")
public class OrderDirectListener {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void process(Map<String, String> map) {
        // 模拟处理用户订单信息
        logger.info("directExchange Received mq message success:{}", map);
    }

}