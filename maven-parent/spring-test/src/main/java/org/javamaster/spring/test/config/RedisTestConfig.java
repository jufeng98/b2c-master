package org.javamaster.spring.test.config;

import cn.com.bluemoon.redis.lock.RedissonDistributedLocker;
import cn.com.bluemoon.redis.repository.RedisRepository;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.javamaster.spring.test.GeneralTestCode.PROFILE_UNIT_TEST;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yudong
 * @date 2021/5/13
 **/
@SuppressWarnings("all")
@TestConfiguration
@Profile(PROFILE_UNIT_TEST)
public class RedisTestConfig {

    @Value("${spring.redis.cluster.nodes}")
    private String nodes;
    @Value("${spring.redis.cluster.password}")
    private String password;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        List<String> nodes = Arrays.stream(this.nodes.split(",")).collect(Collectors.toList());
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(nodes);
        clusterConfiguration.setPassword(password);
        return new LettuceConnectionFactory(clusterConfiguration);
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    public RedisRepository redisRepository(RedisTemplate redisTemplate) {
        return new RedisRepository(redisTemplate);
    }


    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useClusterServers()
                .addNodeAddress(Arrays.stream(nodes.split(",")).map(x -> "redis://" + x).toArray(String[]::new))
                .setPassword(password);
        return Redisson.create(config);
    }

    @Bean
    public RedissonDistributedLocker redissonDistributedLocker(RedissonClient redissonClient) {
        RedissonDistributedLocker locker = new RedissonDistributedLocker(redissonClient);
        return locker;
    }

}
