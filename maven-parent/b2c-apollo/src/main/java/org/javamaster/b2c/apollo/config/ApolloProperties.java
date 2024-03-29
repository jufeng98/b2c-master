package org.javamaster.b2c.apollo.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.*;
import org.javamaster.b2c.apollo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author yudong
 * @date 2019/10/29
 */
@Configuration
public class ApolloProperties implements CommandLineRunner {

    @Value("${timeout:1000}")
    private int timeout;

    @ApolloConfig
    private Config config;

    @Autowired
    private RedisProperties redisProperties;

    @ApolloJsonValue("${persons:[]}")
    private List<Person> persons;

    @Override
    public void run(String... args) {

        // 监听配置变化事件
        Config config1 = ConfigService.getAppConfig();
        config1.addChangeListener(changeEvent -> {
            System.out.println("Changes for namespace " + changeEvent.getNamespace());
            for (String key : changeEvent.changedKeys()) {
                ConfigChange change = changeEvent.getChange(key);
                System.out.printf("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s%n",
                        change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType());
            }
        });

        System.out.println("value注解:" + timeout);
        System.out.println("ApolloConfig注解:" + config.getIntProperty("timeout", 1000));
        System.out.println("API方式调用:" + ConfigService.getAppConfig().getIntProperty("timeout", 1000));
        System.out.println("redis:" + redisProperties);
        System.out.println("json:" + persons);
    }

    @ApolloConfigChangeListener
    private void someOnChange(ConfigChangeEvent changeEvent) {
        System.out.println("Changes1 for namespace " + changeEvent.getNamespace());
        for (String key : changeEvent.changedKeys()) {
            ConfigChange change = changeEvent.getChange(key);
            System.out.printf("Found1 change - key: %s, oldValue: %s, newValue: %s, changeType: %s%n",
                    change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType());
        }
    }

}
