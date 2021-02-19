package org.javamaster.b2c.jmx.config;

import org.javamaster.b2c.jmx.listener.JmxListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;

import javax.management.NotificationListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2018/12/14.<br/>
 *
 * @author yudong
 */
@Configuration
public class MBeanConfig {

    @Autowired
    private MBeanExporter exporter;

    @Bean
    public JmxListener loginListener() {
        JmxListener loginListener = new JmxListener();
        Map<String, NotificationListener> listeners = new HashMap<>(5);
        // 通过objectName指定listener要监听的通知类
        listeners.put("org.javamaster.b2c:mbean=SendJmxRunner", loginListener);
        exporter.setNotificationListenerMappings(listeners);
        return loginListener;
    }

}
