package org.javamaster.b2c.kafka;

import org.javamaster.b2c.config.B2cMasterConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yudong
 * @date 2019/12/17
 */
@Configuration
@AutoConfigureBefore(KafkaAutoConfiguration.class)
public class Config {

    @Autowired
    private KafkaProperties kafkaProperties;

    @PostConstruct
    public void init() {
        String[] cluster = B2cMasterConsts.Server.KAFKA_CLUSTER.split(",");
        kafkaProperties.setBootstrapServers(new ArrayList<>(Stream.of(cluster).collect(Collectors.toList())));
    }
}
