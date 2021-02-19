package org.javamaster.b2c.rabbitmq.constant;

/**
 * @author yu
 * @date 2018/4/30
 */
public interface JmsConst {
    String ORDER_ROUTING_DIRECT_KEY = "rabbit.queue.direct";

    String ORDER_ROUTING_TOPIC_KEY = "rabbit.topic.order";


    String ORDER_TOPIC_QUEUE = "queueMessage";
    String ORDER_TOPIC_QUEUE1 = "queueMessage1";
    String ORDER_TOPIC_QUEUE2 = "queueMessage2";

}
