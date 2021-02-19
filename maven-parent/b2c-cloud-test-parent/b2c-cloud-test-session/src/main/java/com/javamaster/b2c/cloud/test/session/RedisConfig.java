package com.javamaster.b2c.cloud.test.session;

import org.javamaster.b2c.config.B2cMasterConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisClusterConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yu on 2018/4/29.
 */
@Configuration
public class RedisConfig {

    Logger logger = LoggerFactory.getLogger(getClass());
    // @Value("${common.redis.hostName}")
    private String hostName = B2cMasterConsts.Local.REDIS_URL;
    // @Value("${common.redis.port}")
    private int port = B2cMasterConsts.Local.REDIS_PORT;

    // @Bean
    public JedisClusterConnection jedisClusterConnection() {
        Set<HostAndPort> set = new HashSet<>();
        set.add(new HostAndPort(hostName, port));
        JedisCluster cluster = new JedisCluster(set);
        JedisClusterConnection clusterConnection = new JedisClusterConnection(cluster);
        return clusterConnection;
    }

    @Bean
    public RedisConnection redisConnection(RedisConnectionFactory factory) {
        return factory.getConnection();
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        logger.info("redisConnectionFactory start to create redis factory,host:{},port:{}", hostName, port);
        factory.setHostName(hostName);
        factory.setPort(port);
        return factory;
    }

    @Bean
    @Primary
    public RedisTemplate<String, Serializable> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

}
