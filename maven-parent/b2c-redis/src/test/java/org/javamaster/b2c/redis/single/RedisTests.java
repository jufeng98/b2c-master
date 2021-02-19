package org.javamaster.b2c.redis.single;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.bytebuddy.utility.RandomString;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.javamaster.b2c.config.B2cMasterConsts;
import org.javamaster.b2c.redis.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author yudong
 * @date 2020/10/23
 */

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RedisConfig.class)
@ActiveProfiles({"dev", "test"})
public class RedisTests {

    @Autowired(required = false)
    private RedisTemplate<Object, Object> redisTemplateDev;
    @Autowired(required = false)
    private Jedis jedisDev;
    @Autowired(required = false)
    @Qualifier("redisTemplateClusterTest")
    private RedisTemplate<Object, Object> redisTemplateClusterTest;
    @Autowired(required = false)
    @Qualifier("redisTemplateTest")
    private RedisTemplate<Object, Object> redisTemplateTest;
    @Autowired(required = false)
    @Qualifier("redisTemplateClusterPrd")
    private RedisTemplate<Object, Object> redisTemplateClusterPrd;

    @Test
    public void testDev() {
        String REDIS_TEST_KEY = "B2C:CLOUD:TEST:COMPANY";
        redisTemplateDev.opsForValue().set(REDIS_TEST_KEY, "bluemoon");
        Object object = redisTemplateDev.opsForValue().get(REDIS_TEST_KEY);
        log.info("{}", object);
        redisTemplateDev.delete(REDIS_TEST_KEY);
    }

    @Test
    public void testDev1() {
        RedisConnection conn = Objects.requireNonNull(redisTemplateDev.getConnectionFactory()).getConnection();
        // 字符串get set del
        //set hello world
        redisTemplateDev.opsForValue().set("hello", "world");
        //get hello
        log.info("{}", redisTemplateDev.opsForValue().get("hello"));
        //del hello
        conn.del(new StringRedisSerializer().serialize("hello"));
        log.info("{}", redisTemplateDev.opsForValue().get("hello"));

        jedisDev.set("name", "liangyudong");
        log.info("{}", jedisDev.get("name"));
        log.info("{}", jedisDev.append("name", " jufeng98"));
        log.info("{}", jedisDev.getrange("name", 12, jedisDev.strlen("name")));
        log.info("{}", jedisDev.setrange("name", 12, "yu"));
        log.info("{}", jedisDev.get("name"));
        log.info("{}", jedisDev.getbit("name", 0));
        log.info("{}", jedisDev.setbit("name", 0, true));
        log.info("{}", jedisDev.get("name"));
        log.info("{}", jedisDev.bitcount("name"));
        jedisDev.del("name");
    }

    @Test
    public void testDev2() {
        // 列表rpush lrange lindex lpop
        //rpush list-key item
        log.info("{}", redisTemplateDev.opsForList().rightPush("list-key", "item"));
        log.info("{}", redisTemplateDev.opsForList().rightPush("list-key", "item1"));
        //rpop list-key
        log.info("{}", redisTemplateDev.opsForList().rightPop("list-key"));
        //lpush list-key item2
        log.info("{}", redisTemplateDev.opsForList().leftPush("list-key", "item2"));
        log.info("{}", redisTemplateDev.opsForList().leftPush("list-key", "item3"));
        //lpop list-key
        log.info("{}", redisTemplateDev.opsForList().leftPop("list-key"));
        //lindex list-key 1
        log.info("{}", redisTemplateDev.opsForList().index("list-key", 1));
        log.info("{}", redisTemplateDev.opsForList().range("list-key", -2, -1));
        //lrange list-key 0 -1
        //0为起始索引,-1为结束索引
        log.info("{}", redisTemplateDev.opsForList().range("list-key", 0, -1));

        jedisDev.lpush("alphabet", "b");
        jedisDev.lpush("alphabet", "a");
        jedisDev.rpush("alphabet", "c");
        jedisDev.rpush("alphabet", "d");
        log.info("{}", jedisDev.lrange("alphabet", 0, jedisDev.llen("alphabet")));
        log.info("{}", jedisDev.lindex("alphabet", 2));
        log.info("{}", jedisDev.lpop("alphabet"));
        log.info("{}", jedisDev.lrange("alphabet", 0, jedisDev.llen("alphabet")));
    }

    @Test
    public void testDev3() {
        // 有序键值对 zadd zrange zrangebyscore zrem
        //zadd zset-key 222 member1
        log.info("{}", redisTemplateDev.opsForZSet().add("zset-key", "member1", 222));
        log.info("{}", redisTemplateDev.opsForZSet().add("zset-key", "member2", 333));
        log.info("{}", redisTemplateDev.opsForZSet().add("zset-key", "member5", 555));
        //zrange zset-key 0 -1
        log.info("{}", redisTemplateDev.opsForZSet().range("zset-key", 0, -1));
        //zrange zset-key 0 -1 withscores 按分值大小排序返回
        log.info("{}", redisTemplateDev.opsForZSet().rangeByScore("zset-key", 0, -1));
        //zrangebyscore zset-key 100 350 withscores
        log.info("{}", redisTemplateDev.opsForZSet().rangeByScoreWithScores("zset-key", 100, 350));
        //zrem zset-key member5
        log.info("{}", redisTemplateDev.opsForZSet().remove("zset-key", "member5"));

        jedisDev.zadd("scores", 98, "liangyudong");
        jedisDev.zadd("scores", 92, "Jack");
        jedisDev.zadd("scores", 93, "Rose");
        log.info("{}", jedisDev.zrange("scores", 0, -1));
        log.info("{}", jedisDev.zrangeByScore("scores", 90, 95));
        log.info("{}", jedisDev.zrem("scores", "liangyudong"));

    }

    @Test
    public void testIncrease() {
        log.info("{}", jedisDev.incr("count"));
        log.info("{}", jedisDev.decr("count"));
        log.info("{}", jedisDev.incrBy("count", 10));
        log.info("{}", jedisDev.decrBy("count", 10));
        log.info("{}", jedisDev.incrByFloat("count", 0.5));
    }

    @Test
    public void testDev4() {
        jedisDev.sadd("alphabet", "a", "b", "b", "c");
        jedisDev.sadd("bet", "b", "d");
        log.info("{}", jedisDev.smembers("alphabet"));
        log.info("{}", jedisDev.sismember("alphabet", "a"));
        log.info("{}", jedisDev.srem("alphabet", "a"));
        log.info("{}", jedisDev.sinter("alphabet", "bet"));
        log.info("{}", jedisDev.sunion("alphabet", "bet"));
        log.info("{}", jedisDev.sdiff("alphabet", "bet"));
    }

    @Test
    @SuppressWarnings("ALL")
    public void testMessageQueue() throws InterruptedException {
        String channelName = "orderCode";
        Executors.newCachedThreadPool().submit(
                () -> redisTemplateDev.getConnectionFactory()
                        .getConnection()
                        .subscribe((message, pattern) -> {
                            log.info("{}", "message:" + message);
                            log.info("{}", "pattern:" + new String(pattern));
                        }, channelName.getBytes())
        );

        while (true) {
            TimeUnit.SECONDS.sleep(3);
            String code = RandomString.make(10);
            log.info("{}", "produce code:" + code);
            redisTemplateDev.getConnectionFactory()
                    .getConnection()
                    .publish(channelName.getBytes(), code.getBytes());
        }
    }

    @Test
    public void testTransaction() throws InterruptedException {
        RedisConnectionFactory redisConnectionFactory = redisTemplateDev.getConnectionFactory();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                assert redisConnectionFactory != null;
                log.info("{}", redisConnectionFactory.getConnection().incr("count".getBytes()));
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                redisConnectionFactory.getConnection().incrBy("count".getBytes(), -1);
            });
            thread.start();
        }
        TimeUnit.SECONDS.sleep(2);
        log.info("{}", jedisDev.get("count"));

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                assert redisConnectionFactory != null;
                RedisConnection connection = redisConnectionFactory.getConnection();
                connection.openPipeline();
                connection.multi();
                log.info("{}", connection.incr("count1".getBytes()));
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                connection.incrBy("count1".getBytes(), -1);
                connection.exec();
                connection.closePipeline();
            });
            thread.start();
        }
        TimeUnit.SECONDS.sleep(2);
        log.info("{}", jedisDev.get("count1"));
    }

    @Test
    public void testExpire() throws InterruptedException {
        jedisDev.set("name", "liangyudong");
        jedisDev.expire("name", 3);
        TimeUnit.SECONDS.sleep(2);
        log.info("{}", jedisDev.ttl("name"));
        TimeUnit.SECONDS.sleep(3);
        log.info("{}", jedisDev.get("name"));

        jedisDev.set("name", "liangyudong");
        jedisDev.expireAt("name", System.currentTimeMillis() + 1000);
        TimeUnit.SECONDS.sleep(2);
        log.info("{}", jedisDev.get("name"));

    }

    @Test
    public void test() {
        // 哈希表hset hget hgetall hdel
        //hset hash-key name "liang yudong"
        redisTemplateDev.opsForHash().put("hash-key", "name", "liang yudong");
        redisTemplateDev.opsForHash().put("hash-key", "age", "22");
        redisTemplateDev.opsForHash().put("hash-key", "country", "China");
        //hgetall hash-key
        log.info("{}", redisTemplateDev.opsForHash().entries("hash-key"));
        //hdel hash-key country
        log.info("{}", redisTemplateDev.opsForHash().delete("hash-key", "country"));

        jedisDev.hset("person", "name", "liangyudong");
        jedisDev.hset("person", "age", "23");
        log.info("{}", jedisDev.hget("person", "name"));
        log.info("{}", jedisDev.hgetAll("person"));
        log.info("{}", jedisDev.hdel("person", "name"));
        log.info("{}", jedisDev.hgetAll("person"));
    }

    @Test
    public void test1() throws Exception {
        JedisConnectionFactory factoryTest = (JedisConnectionFactory) redisTemplateTest.getConnectionFactory();
        assert factoryTest != null;
        byte[] data = factoryTest.getConnection().get("1472B8B06CF8C1BEFCE8173F4351CDCE".getBytes(StandardCharsets.UTF_8));
        assert data != null;
        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(data));
        ObjectInputStream ois = new ObjectInputStream(bis);
        log.info("createTime:" + ois.readLong());
        log.info("createTime:" + ois.readObject());
        log.info("lastAccessedTime:" + ois.readObject());
        log.info("maxInactiveInterval:" + ois.readObject());
        log.info("isNew:" + ois.readObject());
        log.info("isValid:" + ois.readObject());
        log.info("thisAccessedTime:" + ois.readObject());
        log.info("id:" + ois.readObject());

        int count = (int) ois.readObject();
        log.info("attribute count:" + count);
        for (int i = 0; i < count; i++) {
            Object name = ois.readObject();
            Object value = ois.readObject();
            log.info(name + " " + value);
        }
    }

    @Test
    public void test2() {
        log.info("{}", redisTemplateClusterTest.opsForValue().get("BM_EC:service_app_manager:null:name"));
    }

    @Test
    public void test3() {
        JedisConnectionFactory factory = (JedisConnectionFactory) redisTemplateClusterTest.getConnectionFactory();
        assert factory != null;
        RedisClusterConnection connection = factory.getClusterConnection();
        Set<byte[]> keys = connection.keys("washing:mana:*".getBytes(StandardCharsets.UTF_8));
        assert keys != null;
        for (byte[] key : keys) {
            DataType dataType = connection.type(key);
            assert dataType != null;
            log.info(new String(key) + " " + dataType.code());
            if (dataType == DataType.HASH) {
                Objects.requireNonNull(connection.hGetAll(key)).forEach((hkey, hvalue) -> log.info(new String(hkey) + " " + new String(hvalue)));
            } else if (dataType == DataType.SET) {
                for (byte[] sMember : Objects.requireNonNull(connection.sMembers(key))) {
                    log.info(new String(sMember));
                }
            } else {
                log.info(new String(key));
            }
        }
    }

    public static final ExecutorService executorService = Executors.newCachedThreadPool();

    @Test
    @SneakyThrows
    public void testCluster() {
        String pattern = "BM_EC:service_app_manager:getBannerList*";
        List<Callable<Set<byte[]>>> tasks = Lists.newArrayList();
        for (String url : B2cMasterConsts.Honor.REDIS_CLUSTER_2.split(",")) {
            tasks.add(() -> getNodeKeys(url, B2cMasterConsts.Honor.REDIS_CLUSTER_PASSWD, pattern));
        }
        List<Future<Set<byte[]>>> futures = executorService.invokeAll(tasks);
        Set<byte[]> keyBytesSet = Sets.newHashSet();
        for (Future<Set<byte[]>> future : futures) {
            keyBytesSet.addAll(future.get());
        }
        JedisConnectionFactory factory = (JedisConnectionFactory) redisTemplateClusterTest.getConnectionFactory();
        RedisClusterConnection connection = null;
        try {
            assert factory != null;
            connection = factory.getClusterConnection();
            for (byte[] keyBytes : keyBytesSet) {
                String key = new String(keyBytes, StandardCharsets.UTF_8);
                byte[] valueBytes = connection.get(keyBytes);
                assert valueBytes != null;
                String value = new String(valueBytes, StandardCharsets.UTF_8);
                Long seconds = connection.ttl(keyBytes, TimeUnit.SECONDS);
                log.info("key:\r\n{}\r\nvalue:\r\n{}\r\ntimeout:{}s", key, value, seconds);
            }
            // 执行删除操作
            // byte[][] keyBytes = new byte[keyBytesSet.size()][];
            // keyBytesSet.toArray(keyBytes);
            // connection.del(keyBytes);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Test
    @SneakyThrows
    public void testCluster1() {
        String pattern = "BM_EC:service_app_manager:getBannerList*";
        val keys = redisTemplateClusterTest.keys(pattern);
        assert keys != null;
        keys.forEach(key -> {
            String value = (String) redisTemplateClusterTest.opsForValue().get(key);
            Long seconds = redisTemplateClusterTest.getExpire(key, TimeUnit.SECONDS);
            log.info("key:\r\n{}\r\nvalue:\r\n{}\r\ntimeout:{}s", key, value, seconds);
        });
        // 执行删除操作
        // redisTemplateClusterTest.delete(keys);
        log.info("end");
    }

    public static Set<byte[]> getNodeKeys(String url, String password, String pattern) {
        String[] urls = url.split(":");
        String host = urls[0];
        int port = Integer.parseInt(urls[1]);
        Set<byte[]> keyBytesSet = Sets.newHashSet();
        Set<String> keys = Sets.newHashSet();
        JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory) newRedisTemplate(host, port, password).getConnectionFactory();
        RedisConnection connection = null;
        try {
            assert jedisConnectionFactory != null;
            connection = jedisConnectionFactory.getConnection();
            ScanOptions scanOptions = ScanOptions.scanOptions().match(pattern).count(Integer.MAX_VALUE).build();
            Cursor<byte[]> cursor = connection.scan(scanOptions);
            while (cursor.hasNext()) {
                byte[] keyBytes = cursor.next();
                keyBytesSet.add(keyBytes);
                keys.add(new String(keyBytes, StandardCharsets.UTF_8));
            }
            log.info("thread id:{},url:{},keyBytesSet:{}", Thread.currentThread().getId(), url, keys);
        } catch (Exception e) {
            log.error("error:{},{}", url, e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return keyBytesSet;
    }

    public static RedisTemplate<Object, Object> newRedisTemplate(String host, int port, String password) {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPort(port);
        configuration.setPassword(password);
        JedisConnectionFactory factory = new JedisConnectionFactory(configuration);
        factory.afterPropertiesSet();

        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
