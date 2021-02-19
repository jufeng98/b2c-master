package com.javamaster.b2c.cloud.test.redis;

import com.javamaster.b2c.cloud.test.common.model.Result;
import com.netflix.discovery.EurekaClient;
import com.sun.nio.zipfs.ZipInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yu on 2018/4/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class RestTest {
    @Autowired
    private RestTemplate rest;
    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private EurekaInstanceConfigBean bean;

    @Test
    public void testMongo() {
        String string = rest.getForObject("http://b2c-cloud-ordermanagement/orderManagement/packagetime/version",
                String.class);
        System.out.println(string);
    }

    @Test
    public void testLoader() {
        System.out.println(Object.class.getClassLoader());
        System.out.println(ZipInfo.class.getClassLoader());
        System.out.println(Result.class.getClassLoader());
    }
}
