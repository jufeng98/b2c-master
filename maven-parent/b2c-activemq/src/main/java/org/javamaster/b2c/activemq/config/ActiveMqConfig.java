package org.javamaster.b2c.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTempTopic;
import org.javamaster.b2c.activemq.constant.JmsConst;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * @author yudong
 * @date 2018/4/30
 */
@Configuration
public class ActiveMqConfig {

    @Bean
    public ConnectionFactory activemqConnectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        String url = "tcp://127.0.0.1:61616";
        factory.setBrokerURL(url);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory factory) {
        JmsTemplate template = new JmsTemplate(factory);
        template.setDefaultDestination(new ActiveMQQueue(JmsConst.ACTIVE_MQ_QUEUE));
        template.setDefaultDestination(new ActiveMQTempTopic(JmsConst.ACTIVE_MQ_TOPIC));
        return template;
    }

}
