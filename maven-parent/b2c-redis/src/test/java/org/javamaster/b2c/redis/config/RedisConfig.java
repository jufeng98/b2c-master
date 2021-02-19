package org.javamaster.b2c.redis.config;

import org.javamaster.b2c.config.B2cMasterConsts;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;

/**
 * @author yudong
 * @date 2020/10/23
 */
@Configuration
@PropertySource("classpath:application-test.properties")
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;

    @Bean
    @Primary
    @Profile("dev")
    public Jedis jedisDev() {
        Jedis jedisDev = new Jedis(B2cMasterConsts.Local.REDIS_URL, B2cMasterConsts.Local.REDIS_PORT);
        jedisDev.auth(B2cMasterConsts.Local.REDIS_PASSWORD);
        return jedisDev;
    }

    @Bean
    @Primary
    @Profile("dev")
    public RedisTemplate<Object, Object> redisTemplateDev(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    @Primary
    @Profile("dev")
    public JedisConnectionFactory jedisConnectionFactoryDev() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPort(port);
        configuration.setPassword(password);
        return new JedisConnectionFactory(configuration);
    }

    @Bean
    @Profile("test")
    public RedisTemplate<Object, Object> redisTemplateTest(@Qualifier("jedisConnectionFactoryTest")
                                                                   JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    @Profile("test")
    public JedisConnectionFactory jedisConnectionFactoryTest() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(B2cMasterConsts.WashingService.REDIS_URL,
                B2cMasterConsts.WashingService.REDIS_PORT);
        configuration.setPassword(B2cMasterConsts.WashingService.REDIS_PASSWORD);
        configuration.setDatabase(10);
        return new JedisConnectionFactory(configuration);
    }

    @Bean
    @Profile("test")
    public RedisTemplate<Object, Object> redisTemplateClusterTest(@Qualifier("jedisConnectionFactoryClusterTest")
                                                                          JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    @Profile("test")
    public JedisConnectionFactory jedisConnectionFactoryClusterTest() {
        RedisClusterConfiguration configuration = new RedisClusterConfiguration();
        String[] urls = B2cMasterConsts.Honor.REDIS_CLUSTER_2.split(",");
        for (String str : urls) {
            RedisNode redisNode = new RedisNode(str.split(":")[0], Integer.parseInt(str.split(":")[1]));
            configuration.addClusterNode(redisNode);
        }
        configuration.setPassword(B2cMasterConsts.Honor.REDIS_CLUSTER_PASSWD);
        return new JedisConnectionFactory(configuration);
    }

    @Bean
    @Profile("prd")
    public RedisTemplate<Object, Object> redisTemplateClusterPrd(@Qualifier("jedisConnectionFactoryClusterPrd")
                                                                         JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    @Profile("prd")
    public JedisConnectionFactory jedisConnectionFactoryClusterPrd() {
        RedisClusterConfiguration configuration = new RedisClusterConfiguration();
        String[] urls = B2cMasterConsts.Honor.REDIS_CLUSTER_3_OLD.split(",");
        for (String str : urls) {
            RedisNode redisNode = new RedisNode(str.split(":")[0], Integer.parseInt(str.split(":")[1]));
            configuration.addClusterNode(redisNode);
        }
        configuration.setPassword(B2cMasterConsts.Honor.REDIS_CLUSTER_PASSWD_3_OLD);
        return new JedisConnectionFactory(configuration);
    }

}
