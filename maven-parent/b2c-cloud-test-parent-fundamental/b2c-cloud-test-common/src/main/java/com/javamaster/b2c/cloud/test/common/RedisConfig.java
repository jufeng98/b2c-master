package com.javamaster.b2c.cloud.test.common;

import org.javamaster.b2c.config.B2cMasterConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisClusterConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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
//    @Value("${common.redis.hostName}")
    private String hostName = B2cMasterConsts.Local.REDIS_URL;
//    @Value("${common.redis.port}")
    private int port = B2cMasterConsts.Local.REDIS_PORT;
//    @Value("${common.redis.port}")
    private String password = B2cMasterConsts.Local.REDIS_PASSWORD;

    //@Bean
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
        factory.setPassword(password);
        return factory;
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate redisTemplate = new RedisTemplate<>();
        StringRedisSerializer stringRedisSerializer=new StringRedisSerializer();
        JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        return stringRedisTemplate;
    }
}
