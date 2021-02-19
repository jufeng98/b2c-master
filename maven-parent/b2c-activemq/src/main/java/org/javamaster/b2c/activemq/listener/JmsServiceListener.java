package org.javamaster.b2c.activemq.listener;

import org.javamaster.b2c.activemq.constant.JmsConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yudong
 */
@Component
public class JmsServiceListener {
    Logger logger = LoggerFactory.getLogger(getClass());

    @JmsListener(destination = JmsConst.ACTIVE_MQ_QUEUE)
    public void receiveQueue(Map<String, String> map) {
        logger.info("Received mq queue message success:{}", map);
    }

    @JmsListener(destination = JmsConst.ACTIVE_MQ_TOPIC)
    public void receiveTopic(Map<String, String> map) {
        logger.info("Received mq topic message success:{}", map);
    }

}
